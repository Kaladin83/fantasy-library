package com.fantasyLibrary.services;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import org.apache.tika.langdetect.OptimaizeLangDetector;
import org.apache.tika.language.detect.LanguageDetector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fantasyLibrary.Constants;
import com.fantasyLibrary.Constants.Cover;
import com.fantasyLibrary.Constants.Ext;
import com.fantasyLibrary.Constants.Remove;
import com.fantasyLibrary.Constants.Url;
import com.fantasyLibrary.Utils;
import com.fantasyLibrary.data.Mappers;
import com.fantasyLibrary.models.db.Author;
import com.fantasyLibrary.models.db.AuthorRoles;
import com.fantasyLibrary.models.db.Book;
import com.fantasyLibrary.models.db.Edition;
import com.fantasyLibrary.models.extracted.ExtractedBook;
import com.fantasyLibrary.models.extracted.ExtractedCovers;
import com.fantasyLibrary.models.goodreads.AuthorBook;
import com.fantasyLibrary.models.goodreads.author.GoodreadsAuthorSearch;
import com.fantasyLibrary.models.goodreads.showBook.Series;
import com.fantasyLibrary.models.goodreads.showBook.SeriesWork;
import com.fantasyLibrary.models.goodreads.showBook.ShowBook;
import com.fantasyLibrary.models.goodreads.showBook.ShowResponse;
import com.fantasyLibrary.models.goodreads.showBook.WorkObjectShow;
import com.fantasyLibrary.models.openLibrary.BookNum;
import com.fantasyLibrary.models.openLibrary.Details;
import com.fantasyLibrary.models.openLibrary.OpenLibrary;
import com.fantasyLibrary.models.openLibrary.searchByAuthor.Document;
import com.fantasyLibrary.models.openLibrary.searchByAuthor.SearchByAuthorResponse;
import com.fantasyLibrary.repos.AuthorRepository;
import com.fantasyLibrary.repos.AuthorRolesRepository;
import com.fantasyLibrary.repos.BookRepository;
import com.fantasyLibrary.repos.EditionRepository;
import com.fantasyLibrary.repos.SeriesRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class BooksETLService {

	@Autowired
	private AuthorRepository authorRepo;
	
	@Autowired
	private AuthorsETLService authorService;
	
	@Autowired
	private BookRepository bookRepo;
	
	@Autowired
	private EditionRepository editionRepo;
	
	@Autowired
	private SeriesRepository seriesRepo;

	@Autowired
	private AuthorRolesRepository authorRolesRepo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	private LanguageDetector detector = new OptimaizeLangDetector().loadModels();
	
	public static String authorName;
	
	public Map<Long, String> mapOfSeries = new HashMap<Long, String>(); 
	
	private Set<String> setOfCovers = new HashSet<String>();
	
	public void extractBooks() {
		//List<Author> authors = (List<Author>) authorRepo.findAll();
		

		//authors.stream().map(this::getBooksByAuthorId).forEach(this::transformAndLoadBooks);
		
		Author author = authorRepo.findById(38550L).orElse(null);
		//Map<String, List<ExtractedBook>> mapOfBooks = new HashMap<String, List<ExtractedBook>>();
		authorName = author.getFirstName()+" "+author.getLastName();
		Map<String, List<ExtractedBook>> mapOfBooks = 
				transformAndLoadBooks(getBooksByAuthorId(author), author.getGoodreadsId().intValue());
		
		mapOfSeries.entrySet().stream()
			.forEach(s-> populateSeies(s.getKey(), s.getValue()));

		getCoversByAuthor(mapOfBooks);
		
		Map<Integer, List<com.fantasyLibrary.models.extracted.Author>> mapOfAuthors = 
				mapOfBooks.entrySet().stream()
					.flatMap(v-> v.getValue().stream())
					.flatMap(s-> s.getAuthors().stream())
					.collect(groupingBy(com.fantasyLibrary.models.extracted.Author::getId));
		mapOfAuthors.entrySet().forEach(s-> updateSetOfAuthorRoles(s.getValue()));	
	}

	private GoodreadsAuthorSearch getBooksByAuthorId(Author author) {
		int numOfPages = 600;
		String url = Url.BASE_GOODREADS_AUTHOR.value() + author.getGoodreadsId() + 
				Ext.GOODREADS_KEY.value() + Ext.FORMAT.value() + Ext.PER_PAGE.value();
		GoodreadsAuthorSearch searchResponse = 
				Utils.getForObject(GoodreadsAuthorSearch.class, url + numOfPages, restTemplate);

		if (searchResponse == null) {
			return new GoodreadsAuthorSearch();
		}

		numOfPages = searchResponse.getAuthor().getAuthorsBooks().getTotal()
				- searchResponse.getAuthor().getAuthorsBooks().getEnd();

		if (numOfPages > 0) {
			List<AuthorBook> books = searchResponse.getAuthor().getAuthorsBooks().getBooks();
			searchResponse = 
					Utils.getForObject(GoodreadsAuthorSearch.class, url + numOfPages, restTemplate);
			books.addAll(searchResponse.getAuthor().getAuthorsBooks().getBooks());
		}
		return searchResponse;
	}

	private Map<String, List<ExtractedBook>> transformAndLoadBooks(GoodreadsAuthorSearch data, Integer authorId) {
		Map<String, List<ExtractedBook>> groupedBooks = new HashMap<String, List<ExtractedBook>>();
		
		groupedBooks = data.getAuthor().getAuthorsBooks().getBooks().stream()
			.mapToInt(AuthorBook::getId)
			.mapToObj(this::fetchDataFromShowBook)
			.filter(r-> isValid(r, authorId))
			.map(this::mapBookData)
			.collect(groupingBy(ExtractedBook::getSeriesAndSequence));
		
		groupedBooks.entrySet().stream().forEach(e-> printBooks(e.getKey(), e.getValue()));

		return groupedBooks;
	}
	
	private boolean isValid(ShowResponse showResponse, Integer authorId){
		return showResponse != null && showResponse.getBook() != null && 
				isInEnglish(showResponse) ;
	}

	private ShowResponse fetchDataFromShowBook(int bookId) {
		String url = Url.BASE_GOODREADS_SHOW_BOOK.value() + bookId + "?" +
				Ext.GOODREADS_KEY.value() + Ext.FORMAT.value();
		return Utils.getForObject(ShowResponse.class, url, restTemplate);
	}

	private ExtractedBook mapBookData(ShowResponse showResponse) {
		ExtractedBook book = new ExtractedBook();
		ShowBook data = showResponse.getBook();
		
		book.setAuthors(data.getAuthors().stream()
				.map(this::mapToAuthor)
				.collect(toList()));
		book.setCovers(getGoodreadsCover(data));
		book.setAverageRating(data.getAverageRating());
		book.setDescription(data.getDescription());
		book.setEdition(getEdition(data));
		book.setPublicationDate(formDate(data));
		book.setRatingsCount(data.getWork().getRatingsCount());
		book.setTextReviewsCount(data.getTextReviewsCount());
		book.setLanguage(data.getLanguageCode());
		setNames(book, data, data.getSeriesWorks()); 
		
		if (data.getSeriesWorks() != null && data.getSeriesWorks().size() > 0) {
			book.setSeries(IntStream.range(0, data.getSeriesWorks().size())
				.mapToObj(i-> populateSeries(book, i, data, data.getSeriesWorks().get(i)))
				.map(SeriesWork::getSeries)
				.collect(toMap(Series::getId, s-> s.getTitle().strip())));
		}
		return book;
	}
	
	private List<String> getGoodreadsCover(ShowBook data) {
		List<String> covers = new ArrayList<String>();
		if (!Utils.isEmpty(data.getImageUrl()) && !data.getImageUrl().contains("nophoto")) {
			covers.add(data.getImageUrl()
					.replace(Cover.GR_PREFIX.value(), "")
					.replace(Cover.GR_SUFIX.value(), ""));
		}
		return covers;
	}

	private com.fantasyLibrary.models.extracted.Author mapToAuthor (com.fantasyLibrary.models.goodreads.showBook.Author a){
		com.fantasyLibrary.models.extracted.Author author = new com.fantasyLibrary.models.extracted.Author();
		long roleId = setRole(a.getRole());
		
		author.setId(a.getId());
		author.setName(a.getName());
		author.setRole(roleId);
		return author;
	}

	private long setRole(String role) {
		AuthorRoles returnRole = authorRolesRepo.findByRole(role).orElse(null);
		if (returnRole == null) {
			returnRole = authorRolesRepo.save(new AuthorRoles(role));
		}
		return returnRole.getId();
	}

	private boolean isInEnglish(ShowResponse searchResponse) {
		ShowBook book = searchResponse.getBook();
		
		boolean isEnglishDescription = Utils.isEmpty(book.getDescription())? true
				: detector.detect(book.getDescription()).isLanguage("en");
		String languageCode = book.getLanguageCode() == null? "": book.getLanguageCode();
		
		if (!Mappers.getAcceptedLanguages().contains(languageCode)  || !isEnglishDescription || !isReleased(book)){
			return false;
		}
		return true;
	}
	
	private boolean isReleased(ShowBook book) {
		LocalDate localDate = LocalDate.now();
		WorkObjectShow b = book.getWork(); 
		int year = Math.max(b.getOriginalPublicationYear(), book.getPublicationYear());
		int month = Math.max(b.getOriginalPublicationMonth(), book.getPublicationMonth());
		int day = Math.max(b.getOriginalPublicationDay(), book.getPublicationDay());
		if (year == 0 || year > localDate.getYear() || year < 1700) {
			return false;
		}
		if (month  > 12 || (year == localDate.getYear() && month > localDate.getMonthValue())){
			return false;
		}
		
		if (month > 0 && day > 0){
			LocalDate date = LocalDate.of(year, month, day);
			int daysInMonth = date.lengthOfMonth();
			return day > 0 && day <= daysInMonth && date.isBefore(localDate);
		}
		
		return true;
	}

	private SeriesWork populateSeries(ExtractedBook book, int index, ShowBook data, SeriesWork series) {
		if (index == 0) {
			setSequence(book, series);
					
		}
		book.setTitle(extractFromSeries(book, series.getSeries().getTitle().strip()));
		book.setSeriesAndSequence(book.getTitle()+"|"+book.getSubSequence());	
		 
		mapOfSeries.put((long)series.getSeries().getId(), 
				series.getSeries().getTitle().strip().replaceAll("\\s+", " "));
		return series;
	}
	
	private void setNames(ExtractedBook book, ShowBook data, List<SeriesWork> series) {
		if (data.getId() == 25175964 || data.getId() == 25175838 || data.getId() == 6646849) {
			System.out.println("The id is: "+data.getId());
		}
		book.setOriginalTitle(data.getTitle());
		boolean populateSequence = false;
		if ((series == null || series.size() == 0) || 
				series != null && series.size() > 0 && Utils.isEmpty(series.get(0).getUserPosition())) {
			populateSequence = true;
		}
		populateTitleAndSequence(book, data, populateSequence);		
	}


	private void populateTitleAndSequence(ExtractedBook book, ShowBook data, boolean populateSequence) {
		String title = Utils.isEmpty(data.getTitle())? data.getWork().getOriginalTitle(): data.getTitle();
		
		String[] results = extractFromParentheses(title.replaceAll("\\s+", " "));
		title = results[0].strip();
		populateSequence = populateBookSequence(book, results, populateSequence);
		
		results = extractFromWithoutParentheses(title);
		title = results[0].strip();
		populateSequence = populateBookSequence(book, results, populateSequence);
		
		results = extractFromWithSpecialKeys(title);
		title = results[0].strip();
		populateBookSequence(book, results, populateSequence);
		
		title = extractAuthorsName(results[0].strip());
		
		book.setTitle(title);
		book.setSeriesAndSequence(book.getTitle()+"|"+book.getSubSequence());	
	}

	private boolean populateBookSequence(ExtractedBook book, String[] sequence, boolean populateSequence) {
		if (populateSequence) {
			if (sequence[1].equals("")) {
				book.setSequence("N/A");
				book.setSubSequence("N/A");
				return true;
			}
			else{
				book.setSequence(sequence[1]);
				Map<Integer, String> map = new HashMap<Integer, String>();
				map.put((int)book.getEdition().getGoodreadsId(), sequence[0].strip());
				book.setSeries(map);
				return false;
			}
		}

		return true;
	}

	public String[] extractFromParentheses(String title) {
		String[] splitTitle = title.split("[(]");
		if (splitTitle.length > 1) {
			String[] splitPart = splitTitle[1].split(" ");
			String[] res = extractSequence(splitPart, title);
			return new String[] {splitTitle[0].strip(), res[1]};
		}
		
		return new String[] {title.strip(), ""};
	}
	
	public String[] extractFromWithoutParentheses(String title) {
		String[] splitTitle = title.split(" ");
		if (splitTitle.length > 1) {
			return extractSequence(splitTitle, title);
		}

		return new String[] {title.strip(), ""};
	}
	
	public String[] extractFromWithSpecialKeys(String title) {
		String[] splitTitle = title.split("[:-]");
		String newTitle = splitTitle[0];
		if (splitTitle.length > 1) {
			String[] res = new String[] {"",""};
			for (int i = 1; i< splitTitle.length; i++) {
				String[] splitPart = splitTitle[i].split(" ");
				res = extractWithSpecialChar(splitPart, title);
				if (res[0].equals("")) {
					return new String[] {newTitle.strip(), ""};
				}
				newTitle += (i == 1?":": "-") + splitTitle[i];
			}
			return new String[] {title.strip(), ""};
		}

		return new String[] {title.strip(), ""};
	}
	
	public String extractFromSeries(ExtractedBook book, String series) {
		if (series.equals("")) {
			return book.getTitle();
		}
		
		String[] split = book.getTitle().split(series);
		if (split.length == 0) {
			return series;
		}
		
		if (split.length == 1) {
			if (split[0].strip().equalsIgnoreCase("the") || split[0].strip().equalsIgnoreCase("a") || 
					split[0].strip().equalsIgnoreCase("an")) {
				return book.getTitle();
			}
			return Utils.removeSpecialChars(split[0], Constants.Remove.BOTH);
		}
		else {
			String[] nextWords = split[1].strip().split(" ");
			String nextWord = nextWords.length > 1? nextWords[1]: "";
			String sequence = findSequence(nextWords[0], false, nextWord);
			if (sequence != null) {
				String stringToRet = series;
				return stringToRet;
			}
			
		}
		String[] splitTitle = book.getTitle().split("[:-]");
		
		String title = "";
		boolean isSeries = false;
		for (String str: splitTitle) {
			if (!str.strip().equalsIgnoreCase(series)) {
				title += str.strip()+" ";
			}
			else {
				isSeries = true;
			}
		}
		
		return !isSeries? book.getTitle(): title.strip().equals("")? series: title.strip();
	}
	
	public String extractAuthorsName(String bookName) {
		String title = bookName.replaceAll(authorName+"\'s", "");
		int startIndex = 0, endIndex = 0;
		String newName = title.strip();
		String[] parts = title.split("\\s+");
		if (parts.length > 0 && title.contains(authorName)) {
			for (int i = 0; i < parts.length - 1; i++) {
				String name = parts[i] + " " + parts[i+1];
				startIndex = i;
				endIndex = i+1;
				if (name.equals(authorName) &&  i-1 > -1 &&
						(parts[i-1].equalsIgnoreCase("by") || parts[i-1].equalsIgnoreCase("from"))){
					startIndex = i-1;
					return formName(startIndex, endIndex, parts);
				}
				else if (name.equals(authorName)) {
					return formName(startIndex, endIndex, parts);
				}
				
				i++;
			}
		}
		return newName;
	}
	
	private String formName(int startIndex, int endIndex, String[] parts) {
		if (startIndex -1 > -1) {
			parts[startIndex -1] = Utils.removeSpecialChars(parts[startIndex -1], Remove.END);
		}	
		if (endIndex+1 < parts.length - 1) {
			parts[endIndex+1] = Utils.removeSpecialChars(parts[endIndex+1], Remove.START);
		}	
		for (int i = startIndex; i <= endIndex; i++) {
			parts[i] = Utils.removeSpecialChars(parts[i], Remove.BOTH);
		}
		
		String newName = IntStream.range(0, parts.length).filter(j-> j < startIndex || j > endIndex)
				.mapToObj(j -> parts[j].strip()).collect(joining(" "));
		return newName;
	}

	private String[] extractSequence(String[] splitTitle, String title) {
		String[] names = new String[]{title, ""};
		Map<String, String> mappedSequence = new HashMap<String, String>();
		int firstFoundKeywordIndex = -1;
		boolean insertedIntoMap = false;
		for (int i = 0; i < splitTitle.length; i++) {
			if (!splitTitle[i].equals("")) {
				splitTitle[i] = splitTitle[i].equalsIgnoreCase("vs.")? "Versus": splitTitle[i];
				String keyWord = Mappers.getKeyWords().get(splitTitle[i].toLowerCase());					
				String sequenceFound = "";
				if (i+1 < splitTitle.length) {
					String nextWord = i + 2 < splitTitle.length? splitTitle[i+2]: "";
					sequenceFound = findSequence(splitTitle[i+1], keyWord != null, nextWord);
				}
				
				if (keyWord != null && !Utils.isEmpty(sequenceFound)) {
					if (firstFoundKeywordIndex == -1) {
						firstFoundKeywordIndex = i; 
					}
					mappedSequence.put(keyWord, sequenceFound);
					insertedIntoMap = true;
				}
				else{
					String nextWord = i +1 < splitTitle.length? splitTitle[i+1]: "";
					String sequence = getDigitNumber(splitTitle[i]);
					Integer roman = getRomanNumber(splitTitle[i], false, nextWord);
					if (!insertedIntoMap && 
							(splitTitle[i].charAt(0) == '#' && !sequence.equals("")) || roman != null) {
						sequence = sequence == null ? roman+"": sequence;
						return getTitleAndSequence(splitTitle, i, names, sequence);
					}
					insertedIntoMap = false;
				}
			}
		}
		if (mappedSequence.size() > 0) {
			return formSequenceFromMapOfkeywords(splitTitle, names, firstFoundKeywordIndex, mappedSequence);
		}
				
		return names;
	}	
	
	private String[] formSequenceFromMapOfkeywords(String[] splitTitle, String[] names, int firstFoundKeywordIndex,
			Map<String, String> mappedSequence) {
		String[] result = getTitleAndSequence(splitTitle, firstFoundKeywordIndex, names, "");
		String sequence = "";
		
		String res = mappedSequence.get("Book");
		res = res != null? res: mappedSequence.get("Volume");
		if (res != null) {
			sequence += res;
			String sub = mappedSequence.get("Part");
			if (sub != null) {
				sequence += ", Part "+ sub;
			}
		}
		else {
			String sub = mappedSequence.get("Part");
			if (sub != null) {
				sequence += "N/A, Part "+sub;
			}
		}
		result[1] = sequence;
		return result;
	}

	private String findSequence(String str, boolean previousWordInSet, String nextWord) {
		Integer sequenceFromWords = getWordNumber(str.toLowerCase());
		Integer sequenceFromRomans = getRomanNumber(str, previousWordInSet, nextWord);	
		String sequence = getDigitNumber(str);

		return sequenceFromWords != null? sequenceFromWords+"": 
			sequenceFromRomans != null? sequenceFromRomans+"": sequence;
	}
	
	private Integer getWordNumber(String str) {
		if (str == null || str.equals("")) {
			return null;
		}
		return !Character.isLetterOrDigit(str.charAt(str.length()-1))? 
				Mappers.getMapOfNumbers().get(str.substring(0, str.length() - 1).toLowerCase()):
					Mappers.getMapOfNumbers().get(str.toLowerCase());
	}

	private Integer getRomanNumber(String str, boolean previousWordInSet, String next) {
		Integer result = null;
		if (str == null || str.equals("")) {
			return null;
		}
		
		if (previousWordInSet) {
			result =  !Character.isLetterOrDigit(str.charAt(str.length()-1))? 
					Mappers.getMapOfRomanNumbers().get(str.substring(0, str.length() - 1)) :
						Mappers.getMapOfRomanNumbers().get(str);
		}
		
		if (!Character.isLetterOrDigit(str.charAt(str.length()-1)) && str.charAt(str.length()-1) != '.') {
			result =  Mappers.getMapOfRomanNumbers().get(str.substring(0, str.length() - 1));
		}
		
		if (Character.isLetterOrDigit(str.charAt(str.length()-1)) && !next.equals("") &&
				!Character.isLetterOrDigit(next.charAt(0))) {
			result =  Mappers.getMapOfRomanNumbers().get(str);
		}
		return result;
	}
	
	private String getDigitNumber(String str) {
		if (str == null || str.equals("")) {
			return null;
		}
		return !Character.isLetterOrDigit(str.charAt(str.length()-1))? 
				extractNumbersOfSequence(str.substring(0, str.length() - 1)):
					extractNumbersOfSequence(str);
	}

	private String[] extractWithSpecialChar(String[] splitTitle, String title) {
		String[] names = new String[]{title, ""};
		for (int i = 0; i < splitTitle.length; i++) {
			if (!splitTitle[i].equals("")) {
				if(splitTitle[i].equalsIgnoreCase("novela") || (i - 1 > 0 &&
						splitTitle[i].equalsIgnoreCase("novel") && !splitTitle[i-1].equalsIgnoreCase("graphic"))||
					splitTitle[i].equalsIgnoreCase("collection")) {
					return new String[] {"",""};
				}
			}
		}
		return names;
	}	
	
	private String[] getTitleAndSequence(String[] splitTitle, int i, String[] names, String sequence) {
		if (i > 0 && !splitTitle[i-1].equals(" ") && !splitTitle[i-1].equals("") && !Character.isLetterOrDigit(splitTitle[i-1].charAt(splitTitle[i-1].length()-1))) {
			splitTitle[i-1] = splitTitle[i-1].substring(0, splitTitle[i-1].length()-1);
		}
		names[0] = IntStream.range(0, i)
				.filter(k-> !splitTitle[k].equals(" ") && !splitTitle[k].equals(""))
				.mapToObj(k -> splitTitle[k]).collect(joining(" ")).strip();
		
		names[1] = sequence;
		return names;
	}

	private String extractNumbersOfSequence(String search) {
		int index = 0;
		String result = "";
		for (char c: search.toCharArray()) {
			if (result.length() > 0 && ((!Character.isLetterOrDigit(c) && c != '.' && c != '-') || Character.isAlphabetic(c))) {
				return result;
			}
			
			if (Character.isDigit(c)) {
				result += c;
			}
			
			if (index+1 < search.length() && (c == '.' || c == '-') &&
					Character.isDigit(search.charAt(index+1))) {
				result += c;
			}
			
			if (index+1 < search.length() && c == '#' && index == 0 &&
					Character.isDigit(search.charAt(index+1))) {
				result += c;
			}
			index++;
		}
		return result.equals("")? null: result;
	}

	public void setSequence(ExtractedBook book, SeriesWork series) {
		String sequence = "", subSequence = "";		
		String[] sequences = !Utils.isEmpty(series.getUserPosition())? series.getUserPosition().split("[()]"): new String[0];
		if (sequences.length > 1) {
			String[] results = getSequenceFromName(sequences, book);
			sequence = results.length> 0?  results[0].strip(): "N/A";
			subSequence = results.length> 1?  Utils.capitalize(results[1].strip()): "N/A";
		}
		else {
			sequences = !Utils.isEmpty(series.getUserPosition())? series.getUserPosition().split(","): new String[0];
			String[] results = getSequenceFromName(sequences, book);
			sequence = results.length> 0?  results[0].strip(): "N/A";
			subSequence = results.length> 1?   Utils.capitalize(results[1].strip()): "N/A";
		}
		book.setSequence(sequence);
		book.setSubSequence(subSequence);	
	}

	private String[] getSequenceFromName(String[] sequences, ExtractedBook book) {
		String[] extracted = book.getOriginalTitle().split("Part");
		if (sequences.length == 1 && extracted.length > 1) {
			String[] result = new String[2];
			String[] parts = extracted[1].strip().split(" ");
			result[0] = "N/A";
			result[1] = "Part "+findSequence(parts[0], true, parts.length > 1? parts[1]: "");
			return result;
		}
		else if (sequences.length > 1){
			String number = extractNumbersOfSequence(sequences[1].strip());
			sequences[1] = number == null? "N/A": "Part "+ number;
		}
		return sequences;
	}

	private String formDate(ShowBook data) {
		Integer year = data.getWork().getOriginalPublicationYear();
		Integer month = data.getWork().getOriginalPublicationMonth();
		Integer day = data.getWork().getOriginalPublicationDay();
		return year+"/"+month+"/"+day;
	}

	private Edition getEdition(ShowBook data) {
		Edition edition = new Edition();
		edition.setFormat(data.getFormat());
		edition.setIsbn(data.getIsbn13() != null? data.getIsbn13(): data.getIsbn());
		edition.setNumOfPages(data.getNumPages());
		edition.setPublisher(data.getPublisher());
		edition.setGoodreadsId(Long.valueOf(data.getId()));
		return edition;
	}
	
	private void getCoversByAuthor(Map<String, List<ExtractedBook>> booksWithDeatails) {
		int numOfResults = 500;

		String url = Url.BASE_OPEN_LIBRARY_AUTHOR_SEARCH.value() + authorName + 
				Ext.LIMIT.value() + numOfResults;
		SearchByAuthorResponse searchResponse = 
				restTemplate.getForObject(url, SearchByAuthorResponse.class);
		
		Set<String> listOfIsbns = searchResponse.getDocs().stream()
		.filter(this::filterLanguages)
		.filter(s-> s.getIsbns() != null)
		.flatMap(s-> s.getIsbns().stream())
		.filter(s-> isGoodIsbn(s))
		.collect(toSet());
		
		List<String> listOfIsbnList = getListOfIsbnList(listOfIsbns);
		List<ExtractedCovers> extractedCovers = new ArrayList<ExtractedCovers>();
		
		extractedCovers.addAll(listOfIsbnList.stream()
				.flatMap(isbnList -> fetchCovers(isbnList).stream())
				.collect(toList()));
		extractedCovers.stream().forEach(System.out::println);
		
		booksWithDeatails.entrySet().stream()
			.forEach(v-> mapResults(v.getValue(), extractedCovers));
	}

	private void populateSeies(long id, String name) {
		com.fantasyLibrary.models.db.Series series = 
				new com.fantasyLibrary.models.db.Series(id, name);
		seriesRepo.save(series);
	}
	
	private void mapResults(List<ExtractedBook> bookslist, List<ExtractedCovers> coversList) {
		Set<Edition> editions = bookslist.stream()
				.map(ExtractedBook::getEdition)
				.collect(toSet());
		Set<String> covers = bookslist.stream()
				.flatMap(c-> c.getCovers().stream())
				.collect(toSet());
		
		bookslist.stream().forEach(s-> updateAuthors(s.getAuthors()));

		ExtractedBook bestBook = findBestBook(bookslist);
		boolean found = findInCoverList(bestBook, coversList, covers);
		
		if (!found) {
			List<String> isbnList = bestBook.getAuthors().stream()
				.map(a-> getCoversByTitleForAuthor(bestBook.getTitle(), a))
				.findFirst().orElse(new ArrayList<String>());
			if (isbnList.size() > 0) {
				String isbns = isbnList.stream().collect(joining("|"));
				coversList = fetchCovers(isbns);
				findInCoverList(bestBook, coversList, covers);
			}
		}
		populateBook(bookslist.get(0), editions);
	}
	
	private void updateAuthors(List<com.fantasyLibrary.models.extracted.Author> authors) {
		for (int i = 0; i < authors.size(); i++) {
			Author a = authorRepo.findById((long) authors.get(i).getId()).orElse(null);
			if (a == null) {
				a = new Author();
				a.setGoodreadsId((long) authors.get(i).getId());
				authorService.getAuthorDetailsById(a);
			}
		}
	}

	private ExtractedBook findBestBook(List<ExtractedBook> bookslist) {
		Integer maxRatingsCount = bookslist.stream()
			.mapToInt(ExtractedBook::getRatingsCount)
			.max().orElse(0);
		return bookslist.stream()
			.filter(b-> b.getRatingsCount().intValue() == maxRatingsCount.intValue())
			.findFirst().orElse(new ExtractedBook());
	}

	private boolean findInCoverList(ExtractedBook book, List<ExtractedCovers> coversList, Set<String> covers) {
		boolean found = false;
		
		String bTitle = book.getTitle();
		String bSequence = extractNumbersOfSequence(book.getSequence());
		bSequence = bSequence != null? bSequence.replace("#", ""): book.getSequence().strip();
		String bSubSequence = book.getSubSequence();
		for (ExtractedCovers coversObj: coversList) {
			
			String cTitle = coversObj.getTitle();
			String cSequence = extractNumbersOfSequence(coversObj.getSequence());
			cSequence = cSequence != null? cSequence.replace("#", ""): coversObj.getSequence().strip();
			String cSubSequence = coversObj.getSubSequence();
			if ((bTitle.equalsIgnoreCase(cTitle) &&
					bSequence.equals("N/A") && cSequence.equals("N/A") &&
					bSubSequence.equals("N/A") && cSubSequence.equals("N/A") ||
				(bTitle.equalsIgnoreCase(cTitle) &&
					!bSequence.equals("N/A") && !cSequence.equals("N/A") &&
					bSubSequence.equals("N/A") && cSubSequence.equals("N/A") &&
					bSequence.equals(cSequence))  || 
				(bTitle.equalsIgnoreCase(cTitle) &&
					bSubSequence.equals(cSubSequence)))){
				
				for(String cover: coversObj.getCovers()) {
					if (!setOfCovers.contains(cover) && !cover.equals("-1")) {
						covers.add(cover);
						
						book.getEdition().setIsbn(Utils.isEmpty(book.getEdition().getIsbn())? 
								coversObj.getIsbns().get(0): book.getEdition().getIsbn()); 
						setOfCovers.add(cover);
					}
				}
				found = true;
			}
		}
		if (found) {
			book.setCovers(covers.stream().collect(toList()));
			return true;
		}
		return false;
	}
	
	private List<String> getCoversByTitleForAuthor(String title, com.fantasyLibrary.models.extracted.Author author) {
		int numOfResults = 500;
		String authorName = author.getName().replaceAll(" ", "+");
		String searchTitle = title.replaceAll("[ :]", "+");
		
		String url = Url.BASE_OPEN_LIBRARY_SEARCH.value() + searchTitle + Ext.AUTHOR.value() + authorName +
				Ext.LIMIT.value() + numOfResults;
		System.out.println("url: "+url);
		SearchByAuthorResponse searchResponse = 
				restTemplate.getForObject(url, SearchByAuthorResponse.class);
		
		return searchResponse.getDocs().stream()
				.filter(v-> isBookFound(v, author.getName(), title))
				.flatMap(isbns -> isbns.getIsbns().stream())
				.filter(s-> isGoodIsbn(s))
				.collect(toList());
	}

	private boolean isBookFound(Document doc, String name, String title) {
		boolean isAuthorFound = doc.getAuthorNames().stream().anyMatch(a-> a.equalsIgnoreCase(name)); 
		return isAuthorFound && doc.getTitle().strip().equals(title);
	}

	private boolean isGoodIsbn(String s) {
		 Pattern r = Pattern.compile("\\w+");
	     return r.matcher(s).matches() && (s.length() == 13 || s.length() == 10);
	}

	private void populateBook(ExtractedBook b, Set<Edition> editions) {
		String listOfSeries = b.getSeries() != null? b.getSeries().entrySet().stream()
				.map(s-> s.getKey()+"")
				.collect(joining(", ")): "";
		String listOfAuthors = b.getAuthors() != null? b.getAuthors().stream()
				.map(a-> a.getId()+"|"+a.getRole())
				.collect(joining(", ")):"";
		String listOfCovers = b.getCovers() != null? b.getCovers().stream()
				.map(String::valueOf)
				.collect(joining(", ")): "";
		Book book = new Book();
		
		book.setAverageRating(b.getAverageRating());
		book.setDescription(b.getDescription());
		book.setPublicationDate(b.getPublicationDate());
		book.setRatingsCount(b.getRatingsCount());
		book.setTextReviewsCount(b.getTextReviewsCount());
		book.setSequence(b.getSequence()+", "+ b.getSubSequence());
		book.setTitle(b.getTitle());
		book.setListOfAuthors(listOfAuthors);
		book.setCovers(listOfCovers);
		book.setListOfSeries(listOfSeries);
		if (book.getTitle().contains("The Year's Best Dark Fantasy & Horror")) {
			System.out.println(book);
		}
		
		Book b1 = bookRepo.save(book);
		for (Edition edition: editions) {
			edition.setBookId(b1.getId());
			editionRepo.save(edition);
		}
	}

	private List<String> getListOfIsbnList(Set<String> listOfIsbns) {
		int index = 0, charCount = 0;
		StringBuffer isbns = new StringBuffer("");
		List<String> listOfIsbnList = new ArrayList<String>();
		for (String isbn: listOfIsbns) {
			if (charCount > 1000  || index == listOfIsbns.size()-1) {
				listOfIsbnList.add(isbns.toString());
				isbns = new StringBuffer("");
				charCount = 0;
			}
			
			if (charCount > 0) {
				isbns.append("|").append(isbn);
			}
			else {
				isbns.append(isbn);
			}
			charCount += isbn.length()+1;
			index++;
		}
		return listOfIsbnList;
	}
	
	private List<ExtractedCovers> fetchCovers(String isbns) {
		String url = Url.BASE_OPEN_LIBRARY_ISBN_SEARCH.value() + isbns;
		String coversJson = Utils.getForObject(String.class, url, restTemplate);
		
		System.out.println(coversJson);
		List<ExtractedCovers> returnList = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper();
		try {
			Map<String, OpenLibrary> map = 
			    mapper.readValue(coversJson, new TypeReference<Map<String, OpenLibrary>>() {});
			
			returnList = map.entrySet().stream()
					.flatMap(s-> s.getValue().getRecords().getBooksNum().entrySet().stream())
					.filter(s-> s.getValue().getDetails().getDetails().getCovers() != null)
					.filter(s-> s.getValue().getDetails().getDetails().getCovers().size() > 0)
					.map(this::mapToExtractedCovers)
					.collect(toList());
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} 
		return returnList;
	}

	private ExtractedCovers mapToExtractedCovers(Map.Entry<String, BookNum> record){
		
		Details data = record.getValue().getDetails().getDetails();
		ExtractedCovers extractedCovers = new ExtractedCovers();
		String[] results = stripTitle(data.getTitle().strip().replaceAll("\\s+", " "));
	
		extractedCovers.setCovers(data.getCovers().stream()
				.map(String::valueOf)
				.collect(toList()));
		extractedCovers.setIsbns(data.getIsbn_13());
		extractedCovers.setTitle(results[0]);
		String[] sequences = results[1].split(", ");

		extractedCovers.setSequence(sequences.length> 0 && !sequences[0].equals("")? sequences[0]: "N/A");
		extractedCovers.setSubSequence(sequences.length> 1  && !sequences[1].equals("")? sequences[1]: "N/A");
		return extractedCovers;
	}

	private String[] stripTitle(String title) {
		ExtractedBook book = new ExtractedBook();
		String[] results = extractFromParentheses(title);
		String sequence = results[1];
		
		results = extractFromWithoutParentheses(results[0]);
		sequence = !results[1].equals("")? results[1]: sequence; 
		results = extractFromWithSpecialKeys(results[0]);
		sequence = !results[1].equals("")? results[1]: sequence;
		results[0] = extractAuthorsName(results[0]);
		
		String[] sequences = sequence.split(",");
		results[1] = sequence;
		book.setTitle(results[0]);
		for (Map.Entry<Long, String> entry : mapOfSeries.entrySet()) {
			results[0] = extractFromSeries(book, entry.getValue());
			if (!results[0].equalsIgnoreCase(book.getTitle())) {
				break;
			}
		}
		
		book.setTitle(results[0]);
		book.setSequence(sequences.length> 0? sequences[0]: "N/A");
		book.setSubSequence(sequences.length> 1? sequences[1]: "N/A");
		
		return results;
	}
	
	private boolean filterLanguages(Document doc) {
		if (doc.getLanguages() == null || doc.getLanguages().size() == 0) {
			return true;
		}
		boolean result = doc.getLanguages().stream().anyMatch(s-> s.equals("eng"));
		return result;
	}
	
	private void updateSetOfAuthorRoles(List<com.fantasyLibrary.models.extracted.Author> authorsWithRoles) {
		String roles = authorsWithRoles.stream().map(s-> s.getRole()+"").distinct().collect(joining(", "));
		
		Author a = authorRepo.findById((long) authorsWithRoles.get(0).getId()).orElse(null);
		if (a != null) {
			a.setListOfRoles(roles);
			authorRepo.save(a);
		}
	}

	private void printBooks(String series, List<ExtractedBook> data) {
		int index = 0;
		System.out.println("    *************************    ");
		System.out.println("Series name: "+ series);
		for (ExtractedBook d: data) {
			if (index > 0) {
				System.out.println("          --------------        ");
			}
			System.out.println("Original Title: "+ d.getOriginalTitle());
			System.out.println("Title: "+ d.getTitle());
			System.out.println("Sequence: "+ d.getSequence());
			System.out.println("SubSequence: "+ d.getSubSequence());
			System.out.println(d.getEdition());
			System.out.println("Publication Date: "+ d.getPublicationDate());
			System.out.println("Language: "+ d.getLanguage());
			System.out.println("Average Rating: "+ d.getAverageRating());
			System.out.println("Ratings Count: "+ d.getRatingsCount());
			if (d.getSeries() != null && d.getSeries().size() > 0) {
				d.getSeries().entrySet().stream()
				.forEach(s-> System.out.println("series id: "+ s.getKey()+ "   series Name: "+ s.getValue()));
			}
			index++;
		}
	}
}

package com.fantasyLibrary.services;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import org.apache.tika.langdetect.OptimaizeLangDetector;
import org.apache.tika.language.detect.LanguageDetector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fantasyLibrary.Constants.Cover;
import com.fantasyLibrary.Constants.Ext;
import com.fantasyLibrary.Constants.Remove;
import com.fantasyLibrary.Constants.SpecialWords;
import com.fantasyLibrary.Constants.Url;
import com.fantasyLibrary.SortedArrayList;
import com.fantasyLibrary.Utils;
import com.fantasyLibrary.data.Mappers;
import com.fantasyLibrary.models.db.Author;
import com.fantasyLibrary.models.db.AuthorRoles;
import com.fantasyLibrary.models.db.Book;
import com.fantasyLibrary.models.db.Edition;
import com.fantasyLibrary.models.db.Log;
import com.fantasyLibrary.models.extracted.ExtractedBook;
import com.fantasyLibrary.models.extracted.ExtractedCovers;
import com.fantasyLibrary.models.goodreads.AuthorBook;
import com.fantasyLibrary.models.goodreads.author.GoodreadsAuthorSearch;
import com.fantasyLibrary.models.goodreads.showBook.Series;
import com.fantasyLibrary.models.goodreads.showBook.SeriesWork;
import com.fantasyLibrary.models.goodreads.showBook.Shelf;
import com.fantasyLibrary.models.goodreads.showBook.ShowBook;
import com.fantasyLibrary.models.goodreads.showBook.ShowResponse;
import com.fantasyLibrary.models.goodreads.showBook.WorkObjectShow;
import com.fantasyLibrary.models.openLibrary.BookNum;
import com.fantasyLibrary.models.openLibrary.Details;
import com.fantasyLibrary.models.openLibrary.OpenLibrary;
import com.fantasyLibrary.models.openLibrary.searchByAuthor.Document;
import com.fantasyLibrary.models.openLibrary.searchByAuthor.SearchByAuthorResponse;
import com.fantasyLibrary.models.response.Genre;
import com.fantasyLibrary.repos.AuthorRepository;
import com.fantasyLibrary.repos.AuthorRolesRepository;
import com.fantasyLibrary.repos.BookRepository;
import com.fantasyLibrary.repos.EditionRepository;
import com.fantasyLibrary.repos.LogRepository;
import com.fantasyLibrary.repos.SeriesRepository;
import com.fasterxml.jackson.core.type.TypeReference;
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
	private LogRepository logRepo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	private LanguageDetector detector = new OptimaizeLangDetector().loadModels();
	
	public static String authorName;
	
	private Author currentAuthor = null;
	
	public Map<Long, String> mapOfSeries = new HashMap<Long, String>(); 
	
	public Map<String, Integer> mapOfGenres = new HashMap<String, Integer>();
	public Map<String, Integer> mapOfGenresPerBook = new HashMap<String, Integer>();
	
	private Set<String> setOfCovers = new HashSet<String>();
	
	public void extractBooks() {
		List<Author> authors = authorRepo.findAllByOrderByLastNameAsc();
		//Author author = authorRepo.findById(1318193L).orElse(null);
		
		authors.stream().filter(a-> !isLoaded(a)).forEach(this::startExtracting);
	//	startExtracting(author);
	}
	
	private boolean isLoaded(Author a) {
		Log log = logRepo.findById(a.getGoodreadsId()).orElse(null);
		
		if (log == null) {
			return false;
		}
		if (log.getStatus().equals("started")) {
			List<Long> listOfBooks = bookRepo.findAllByListOfAuthors(a.getGoodreadsId()+"");

			if (listOfBooks.size() > 0 ) {
				listOfBooks.stream()
						.forEach(s-> findByBookId(s));
				listOfBooks.stream().forEach(b-> bookRepo.deleteById(b));
				List<com.fantasyLibrary.models.db.Series> list = 
						(List<com.fantasyLibrary.models.db.Series>)seriesRepo.findAll();
				mapOfSeries = list.stream()
						.collect(toMap(com.fantasyLibrary.models.db.Series::getId, s-> s.getName()));
				
			}
			return false;
		}
		return true;
	}
	
	private void findByBookId(Long bookId){
		List<Edition> editionToDelete = editionRepo.findByBookId(bookId);
		editionRepo.deleteAll(editionToDelete);
	}

	private void startExtracting(Author author) {
		currentAuthor = author;
		authorName = author.getFirstName()+" "+author.getLastName();
		Map<String, List<ExtractedBook>> mapOfBooks = 
				transformAndLoadBooks(getBooksByAuthorId(author), author.getGoodreadsId().intValue());
		if (mapOfBooks == null || mapOfBooks.size() == 0) {
			return;
		}
		mapOfSeries.entrySet().stream()
			.forEach(s-> populateSeies(s.getKey(), s.getValue()));

		getCoversByAuthor(mapOfBooks);
		
		Map<Integer, List<com.fantasyLibrary.models.extracted.Author>> mapOfAuthors = 
				mapOfBooks.entrySet().stream()
					.flatMap(v-> v.getValue().stream())
					.flatMap(s-> s.getAuthors().stream())
					.collect(groupingBy(com.fantasyLibrary.models.extracted.Author::getId));
		mapOfAuthors.entrySet().stream()
			.filter(a-> currentAuthor.getGoodreadsId().intValue() == a.getKey().intValue())
			.forEach(s-> updateSetOfAuthorRoles(s.getValue(), mapOfBooks));	
		
	}

	private GoodreadsAuthorSearch getBooksByAuthorId(Author author) {
		int numOfPages = 600;
		String url = Url.BASE_GOODREADS_AUTHOR.value() + author.getGoodreadsId() + 
				Ext.GOODREADS_KEY.value() + Ext.FORMAT.value() + Ext.PER_PAGE.value();
		GoodreadsAuthorSearch searchResponse = 
				Utils.getForObject(GoodreadsAuthorSearch.class, url + numOfPages, restTemplate);

		if (searchResponse == null) {
			return null;
		}

		numOfPages = searchResponse.getAuthor().getAuthorsBooks().getTotal()
				- searchResponse.getAuthor().getAuthorsBooks().getEnd();

		if (numOfPages > 0) {
			List<AuthorBook> books = searchResponse.getAuthor().getAuthorsBooks().getBooks();
			searchResponse = 
					Utils.getForObject(GoodreadsAuthorSearch.class, url + numOfPages, restTemplate);
			if (searchResponse != null && searchResponse.getAuthor() != null && 
					searchResponse.getAuthor().getAuthorsBooks() != null && 
					searchResponse.getAuthor().getAuthorsBooks().getBooks() != null) {
				books.addAll(searchResponse.getAuthor().getAuthorsBooks().getBooks());
			}
		}
		return searchResponse;
	}

	private Map<String, List<ExtractedBook>> transformAndLoadBooks(GoodreadsAuthorSearch data, Integer authorId) {
		Map<String, List<ExtractedBook>> groupedBooks = new HashMap<String, List<ExtractedBook>>();
		if (data == null) {
			return null;
		}
		groupedBooks = data.getAuthor().getAuthorsBooks().getBooks().stream()
			.mapToInt(AuthorBook::getId)
			.mapToObj(this::fetchDataFromShowBook)
			.filter(s-> s != null)
			.filter(r-> isValid(r, authorId))
			.filter(r-> !isExistsInDb(r, authorId))
			.filter(this::isFantasyBook)
			.map(this::mapBookData)
			.collect(groupingBy(ExtractedBook::getSeriesAndSequence));
		
		groupedBooks.entrySet().stream().forEach(e-> printBooks(e.getKey(), e.getValue()));

		return groupedBooks;
	}

	private boolean isValid(ShowResponse showResponse, Integer authorId){
		return showResponse != null && showResponse.getBook() != null && 
				isInEnglish(showResponse) ;
	}

	private boolean isExistsInDb(ShowResponse r, Integer authorId) {
		Edition edition = editionRepo.findById(Long.valueOf(r.getBook().getId())).orElse(null);
		return edition == null? false: true;
	}
	
	private boolean isFantasyBook(ShowResponse showResponse) {
		if (showResponse.getBook().getPopularShelves() == null || showResponse.getBook().getPopularShelves().size() == 0) {
			return false;
		}
		
		return showResponse.getBook().getPopularShelves().stream()
			.anyMatch(s-> Mappers.getMapOfFantasyGenres().containsKey(s.getName()) && s.getCount().intValue() > 5);
	}
	
	private ShowResponse fetchDataFromShowBook(int bookId) {
		String url = Url.BASE_GOODREADS_SHOW_BOOK.value() + bookId + "?" +
				Ext.GOODREADS_KEY.value() + Ext.FORMAT.value();
		return Utils.getForObject(ShowResponse.class, url, restTemplate);
	}

	private ExtractedBook mapBookData(ShowResponse showResponse) {
		logRepo.save(new Log(currentAuthor.getGoodreadsId(), "started"));
		mapOfGenresPerBook = new HashMap<String, Integer>();
		ExtractedBook book = new ExtractedBook();
		ShowBook data = showResponse.getBook();
		
		book.setAuthors(data.getAuthors().stream()
				.map(this::mapToAuthor)
				.collect(toList()));
		book.setCovers(getGoodreadsCover(data));
		book.setAverageRating(data.getAverageRating());
		book.setDescription(data.getDescription());
		book.setEdition(getEdition(data));
		book.setPublicationDate(getPublicationDate(data));
		book.setRatingsCount(data.getWork().getRatingsCount());
		book.setTextReviewsCount(data.getTextReviewsCount());
		book.setLanguage(data.getLanguageCode());
		
		data.getPopularShelves().forEach(this::getGenre);
		
		IntStream.range(0, Math.min(10, data.getPopularShelves().size()))
			.forEach(i-> getOtherGenres(data.getPopularShelves().get(i)));
		
		Map<String, Integer> map = mapOfGenresPerBook.entrySet().stream()
		        .sorted(Entry.comparingByValue(Comparator.reverseOrder()))
		        .collect(toMap(Entry::getKey, Entry::getValue,
		                (x,y)-> {throw new AssertionError();},
		                LinkedHashMap::new
		        ));
		book.setGenres(map.entrySet().stream().map(v-> v.getKey()+"|"+v.getValue()).collect(toList()));
		setNames(book, data, data.getSeriesWorks()); 
		
		if (data.getSeriesWorks() != null && data.getSeriesWorks().size() > 0) {
			book.setSeries(IntStream.range(0, data.getSeriesWorks().size())
				.mapToObj(i-> populateSeries(book, i, data, data.getSeriesWorks().get(i)))
				.map(SeriesWork::getSeries)
				.collect(toMap(Series::getId, 
						s-> s.getTitle().strip().toLowerCase()
							.replaceAll("\\s+", "").replaceAll(SpecialWords.ANTHOLOGY.value(), ""))));
		}
		return book;
	}

	private String getOtherGenres(Shelf shelf) {
		 String genre = Mappers.getMapOfOtherGenres().get(shelf.getName().strip());
		 if (genre != null) {
			 mapOfGenres.put(genre, 
						mapOfGenres.getOrDefault(genre, 0)+shelf.getCount());
			 mapOfGenresPerBook.put(genre, 
						mapOfGenresPerBook.getOrDefault(genre, 0)+shelf.getCount());
		 }
		 return genre;
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
		role = role.toLowerCase().strip();
		AuthorRoles returnRole = authorRolesRepo.findByRole(role).orElse(null);
		if (returnRole == null || !returnRole.getRole().equals(role)) {
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
			LocalDate date;
			try {
				date = LocalDate.of(year, month, day);
			}
			catch(Exception e){
				return false;
			}
			int daysInMonth = date.lengthOfMonth();
			return day > 0 && day <= daysInMonth && date.isBefore(localDate);
		}
		
		return true;
	}
	
	private List<String> getGoodreadsCover(ShowBook data) {
		List<String> covers = new ArrayList<String>();
		if (!Utils.isEmpty(data.getImageUrl()) && !data.getImageUrl().contains("nophoto")) {
			//covers.add(data.getImageUrl()
			//		.replace(Cover.GR_PREFIX.value(), "")
			//		.replace(Cover.GR_SUFIX.value(), ""));
			String img = data.getImageUrl().replace(Cover.GR_PREFIX.value(), "");
			int indexOfDot = img.indexOf("._");
			if (indexOfDot > -1) {
				String sizeOfImg = getDigitNumber(img.substring(indexOfDot));
				img = img.replace(sizeOfImg, "400");
			}
			
			covers.add(img);
		}
		return covers;
	}
	
	private String getPublicationDate(ShowBook data) {
		String pubDate = formDate(data.getWork().getOriginalPublicationYear(), 
				data.getWork().getOriginalPublicationMonth(), 
				data.getWork().getOriginalPublicationDay());

		if (pubDate.equals("")) {
			pubDate = formDate(data.getPublicationYear(), data.getPublicationMonth(), 
					data.getPublicationDay());
		}
		return pubDate;
	}

	private String getGenre(Shelf shelf){
		String genre = Mappers.getMapOfFantasyGenres().get(shelf.getName().strip());
		if (genre == null) {
			genre = Mappers.getMapOfImportantGenres().get(shelf.getName().strip());		
		}
		
		if (genre != null) {
			mapOfGenres.put(genre, 
					mapOfGenres.getOrDefault(genre, 0)+shelf.getCount());
			mapOfGenresPerBook.put(genre, 
					mapOfGenresPerBook.getOrDefault(genre, 0)+shelf.getCount());
		}
		return genre;
	}
	
	private SeriesWork populateSeries(ExtractedBook book, int index, ShowBook data, SeriesWork series) {
		if (index == 0) {
			setSequence(book, series);
					
		}
		book.setTitle(extractFromSeries(book, series.getSeries().getTitle().strip()));
		book.setSeriesAndSequence(book.getTitle()+"|"+book.getSubSequence());	
		 
		mapOfSeries.put((long)series.getSeries().getId(), 
				series.getSeries().getTitle().strip().toLowerCase()
					.replaceAll("\\s+", " ").replaceAll(SpecialWords.ANTHOLOGY.value(), ""));
		return series;
	}
	
	private void setNames(ExtractedBook book, ShowBook data, List<SeriesWork> series) {
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
		
//		String[] split = book.getTitle().split(series);
//		if (split.length == 0) {
//			return series;
//		}
//		
//		if (split.length == 1) {
//			if (split[0].strip().equalsIgnoreCase("the") || split[0].strip().equalsIgnoreCase("a") || 
//					split[0].strip().equalsIgnoreCase("an")) {
//				return book.getTitle();
//			}
//			return Utils.removeSpecialChars(split[0], Constants.Remove.BOTH);
//		}
//		else {
//			String[] nextWords = split[1].strip().split(" ");
//			String nextWord = nextWords.length > 1? nextWords[1]: "";
//			String sequence = findSequence(nextWords[0], false, nextWord);
//			if (sequence != null) {
//				String stringToRet = series;
//				return stringToRet;
//			}
//			
//		}
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
		String[] parts = newName.split("\\s+");
		if (parts.length > 0 && newName.contains(authorName)) {
			
			for (int i = 0; i < parts.length - 1; i++) {

				String name = parts[i] + " " + parts[i+1];
				startIndex = i;
				endIndex = i+1;
				if (name.equals("") || authorName.equals("")) {
					return name;
				}
				
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
		return newName.replaceAll("\\s+", " ");
	}
	
	private String formName(int startIndex, int endIndex, String[] parts) {
		if (startIndex > 0) {
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
							((i+1 == splitTitle.length && sequence!= null) || splitTitle[i].charAt(0) == '#' && sequence!= null) || roman != null) {
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
				if(splitTitle[i].equalsIgnoreCase(SpecialWords.NOVELA.value()) || (i - 1 > 0 &&
						splitTitle[i].equalsIgnoreCase(SpecialWords.NOVEL.value()) && 
						!splitTitle[i-1].equalsIgnoreCase(SpecialWords.GRAPHIC.value())) ||
					splitTitle[i].equalsIgnoreCase(SpecialWords.COLLECTION.value()) 
					|| splitTitle[i].equalsIgnoreCase(SpecialWords.PLAY.value())) {
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
				.filter(k-> !splitTitle[k].equalsIgnoreCase(SpecialWords.ANTHOLOGY.value()))
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

	private String formDate(Integer year, Integer month, Integer day) {
		if (hasValue(year) && hasValue(month) && hasValue(day)) {
			return year+"/"+month+"/"+day;
		}
		
		if (hasValue(year) && !hasValue(month) && !hasValue(day)) {
			return year+"";
		}
		
		if (hasValue(year) && hasValue(month) && !hasValue(day)) {
			return year+"/"+month;
		}
		return "";
	}
	
	private boolean hasValue(Integer val) {
		return val != null && val > 0;
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
//		int numOfResults = 500;
//
//		String url = Url.BASE_OPEN_LIBRARY_AUTHOR_SEARCH.value() + authorName + 
//				Ext.LIMIT.value() + numOfResults;
//		SearchByAuthorResponse searchResponse = 
//				Utils.getForObject(SearchByAuthorResponse.class, url, restTemplate);
//		
//		List<ExtractedCovers> extractedCovers = new ArrayList<ExtractedCovers>();
//		if (searchResponse != null) {
//			List<String> listOfIsbnList = getListOfIsbnList(searchResponse.getDocs());
//			
//			
//			extractedCovers.addAll(listOfIsbnList.stream()
//					.map(isbnList -> fetchCovers(isbnList))
//					.filter(s-> s != null)
//					.flatMap(isbnList -> isbnList.stream())
//					.filter(s-> s != null)
//					.collect(toList()));
//			extractedCovers.stream().forEach(System.out::println);
//		}
		
		
		booksWithDeatails.entrySet().stream()
			.forEach(v-> mapResults(v.getValue()));
		
	}

	private List<String> getListOfIsbnList(List<Document> docs) {
		Set<String> listOfIsbns = docs.stream()
				.filter(this::filterLanguages)
				.filter(s-> s.getIsbns() != null)
				.flatMap(s-> s.getIsbns().stream())
				.filter(s-> isGoodIsbn(s))
				.collect(toSet());
				
		return getListOfIsbnList(listOfIsbns);
	}

	private void populateSeies(long id, String name) {
		com.fantasyLibrary.models.db.Series series = 
				new com.fantasyLibrary.models.db.Series(id, name);
		seriesRepo.save(series);
	}
	
	private void mapResults(List<ExtractedBook> bookslist) {
		Set<Edition> editions = bookslist.stream()
				.map(ExtractedBook::getEdition)
				.collect(toSet());
		Set<String> covers = bookslist.stream()
				.flatMap(c-> c.getCovers().stream())
				.collect(toSet());
		
		bookslist.stream().forEach(s-> updateAuthors(s.getAuthors()));
		ExtractedBook bestBook = findBestBook(bookslist);
		
		List<String> isbnList = getCoversByTitleForAuthor(bestBook.getTitle(), 
				currentAuthor.getFirstName(), currentAuthor.getLastName(), covers);
		boolean found = extractByIsbnList(covers, bestBook, isbnList);
		
		if (!found) {
			isbnList = bestBook.getAuthors().stream()
				.map(a-> getCoversWithTitleAndAuthor(bestBook.getTitle(), a, covers))
				.findFirst().orElse(new ArrayList<String>());

			extractByIsbnList(covers, bestBook, isbnList);
		}
		
		populateBook(bestBook, editions);
	}

	private List<String> getCoversWithTitleAndAuthor(String title, com.fantasyLibrary.models.extracted.Author a, Set<String> covers) {
		String names[] = Utils.distructAuthorName(a.getName());
		return getCoversByTitleForAuthor(title, names[0], names[1], covers);
	}

	private boolean extractByIsbnList(Set<String> covers, ExtractedBook bestBook,
			List<String> isbnList) {
		if (isbnList.size() == 1 && isbnList.get(0).equals("null")) {
			return false;
		}
		List<ExtractedCovers> extractedCovers = new ArrayList<ExtractedCovers>();
		extractedCovers.addAll(isbnList.stream()
				.map(list -> fetchCovers(list))
				.filter(s-> s != null)
				.flatMap(list -> list.stream())
				.collect(toList()));
		if (extractedCovers.size() > 0) {
			return findInCoverList(bestBook, extractedCovers, covers);
		}
		return false;
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
		
		String bTitle = book.getTitle().replaceAll("[?.,:-]", "");
		String bSequence = extractNumbersOfSequence(book.getSequence());
		bSequence = bSequence != null? bSequence.replace("#", ""): book.getSequence().strip();
		String bSubSequence = book.getSubSequence();
		
		for (ExtractedCovers coversObj: coversList) {
			
			String cTitle = coversObj.getTitle().replaceAll("[?.,:-]", "");
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
								coversObj.getIsbns()== null? "": coversObj.getIsbns().get(0): book.getEdition().getIsbn()); 
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
	
	private List<String> getCoversByTitleForAuthor(String title, String firstName, String lastName, Set<String> covers)  {
		int numOfResults = 500;
		String authorName = firstName.replaceAll(" ", "+")+"+"+lastName;
		String searchTitle = title.replaceAll("[ :]", "+");
		
		String url = Url.BASE_OPEN_LIBRARY_SEARCH.value() + searchTitle + Ext.AUTHOR.value() + authorName +
				Ext.LIMIT.value() + numOfResults;
		System.out.println("url: "+url);
		SearchByAuthorResponse searchResponse = 
				Utils.getForObject(SearchByAuthorResponse.class, url, restTemplate);
		if (searchResponse == null) {
			return new ArrayList<String>() {{add("null");}};
		}
		Set<String> listOfIsbns =  searchResponse.getDocs().stream()
				.filter(this::filterLanguages)
				.filter(v-> isBookFound(v, firstName+" "+lastName, title))
				.filter(s -> s.getIsbns() != null && s.getIsbns().size() > 0)
				.peek(s-> addCover(covers, s.getCoverI()))
				.flatMap(isbns -> isbns.getIsbns().stream())
				.filter(s-> isGoodIsbn(s))
				
				.collect(toSet());
				
		return getListOfIsbnList(listOfIsbns);
	}

	private void addCover(Set<String> covers, Integer coverI) {
		if (coverI != null) {
			covers.add(coverI+"");
		}
	}

	private boolean isBookFound(Document doc, String name, String title) {
		boolean isAuthorFound = doc.getAuthorNames().stream().anyMatch(a-> a.equalsIgnoreCase(name)); 
		String[] stripedTitle = stripTitle(doc.getTitle().strip().replaceAll("\\s+", " "));
		title = title.replaceAll("[?.,:-]", "");
		stripedTitle[0] = stripedTitle[0].replaceAll("[?.,:-]", "");
		return isAuthorFound && stripedTitle[0].equalsIgnoreCase(title);
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
		String listOfGenres = b.getGenres() != null? b.getGenres().stream()
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
		book.setListOfGenres(listOfGenres);
		
		if (book.getListOfSeries().length() > 600) {
			book.setCovers(book.getListOfSeries().substring(0, 600));
		}
		
		if (book.getListOfAuthors().length() > 1800) {
			book.setCovers(book.getListOfAuthors().substring(0, 1800));
		}
		
		if (book.getListOfGenres().length() > 500) {
			System.out.println(book.getCovers());
			book.setCovers(book.getListOfGenres().substring(0, 500));
		}
		
		if (book.getCovers().length() > 1600) {
			System.out.println(book.getCovers());
			book.setCovers(book.getCovers().substring(0, 1600));
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
			if (charCount > 150  || index == listOfIsbns.size()-1) {
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
					.filter(this::coversAndTitleExists)
					.map(this::mapToExtractedCovers)
					.collect(toList());
		} catch (Exception e) {
			e.printStackTrace();
			returnList = null;
		} 
		return returnList;
	}

	private boolean coversAndTitleExists(Map.Entry<String, BookNum> record) {
		Details data = record.getValue().getDetails().getDetails();
		if (data != null && !Utils.isEmpty(data.getTitle()) && data.getCovers() != null && data.getCovers().size() > 0) {
			return true;
		}
		return false;
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
		boolean result = doc.getLanguages().stream().anyMatch(s-> s.contains("eng"));
		return result;
	}
	
	private void updateSetOfAuthorRoles(List<com.fantasyLibrary.models.extracted.Author> authorsWithRoles, 
			Map<String, List<ExtractedBook>> books) {
//		List<Genre> allGenres = books.entrySet().stream()
//				.map(value-> value.getValue())
//				.flatMap(listOfBooks-> listOfBooks.stream())
//				.flatMap(book -> book.getGenres().stream())
//				.map(this::mapToGenre)
//				.collect(toList());
		if (currentAuthor.getGoodreadsId().intValue() == 12540 || currentAuthor.getGoodreadsId().intValue() == 57926 ||
				currentAuthor.getGoodreadsId().intValue() == 17613 || currentAuthor.getGoodreadsId().intValue()== 16881 ||
						currentAuthor.getGoodreadsId().intValue() == 10657 || currentAuthor.getGoodreadsId().intValue()== 2918731)
		{
			System.out.println("Found");
		}
		String topGenres = "";
		Map<String, Integer> map = mapOfGenres.entrySet().stream()
		        .sorted(Entry.comparingByValue(Comparator.reverseOrder()))
		        .collect(toMap(Entry::getKey, Entry::getValue,
		                (x,y)-> {throw new AssertionError();},
		                LinkedHashMap::new
		        ));
				
				
				int index = 0;
				for (Map.Entry<String, Integer> entry: map.entrySet()) {
					if (index == 5) {
						break;
					}
					if (index != 0) {
						topGenres += ", " + entry.getKey()+"|"+ entry.getValue();
					}
					else {
						topGenres += entry.getKey()+"|"+ entry.getValue();
					}
					index++;
				}
		
		//String allGenres = IntStream.range(0, Math.min(5, map.size())).map(i-> map.entrySet().).collect(joining(", "))
		//String topGenres = getTopGenres(allGenres);
		String roles = authorsWithRoles.stream().map(s-> s.getRole()+"").distinct().collect(joining(", "));
		
		Author a = authorRepo.findById(currentAuthor.getGoodreadsId()).orElse(null);
		//Author a = authorRepo.findById((long) authorsWithRoles.get(0).getId()).orElse(null);
		if (a != null) {
			a.setTopGenres(topGenres);
			mapOfGenres = new HashMap<String, Integer>();
			a.setListOfRoles(roles);
			authorRepo.save(a);	
		}
		logRepo.save(new Log(currentAuthor.getGoodreadsId(), "ended"));
	}

	private Genre mapToGenre(String genreWithCount) {
		String[] splitGenre = genreWithCount.split("[|]");
		return new Genre(splitGenre[0], Integer.parseInt(splitGenre[1]));
	}
	
	private String getTopGenres(List<Genre> allGenres) {
		Map<String, List<Genre>> map = allGenres.stream()
				.collect(groupingBy(Genre::getName));
		
		SortedArrayList<Genre> summedBooks = new SortedArrayList<Genre>();
		for (Map.Entry<String, List<Genre>> list : map.entrySet()) {
			int sum = 0;
			for (Genre genre: list.getValue()) {
				sum += genre.getCount();
			}
			summedBooks.insertSorted(new Genre(list.getKey(), sum));
		}
		
		return IntStream.range(0, Math.min(5, summedBooks.size()))
				.mapToObj(i-> summedBooks.get(i).getName()+"|"+ summedBooks.get(i).getCount())
				.collect(joining(", "));
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

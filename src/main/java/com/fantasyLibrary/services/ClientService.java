package com.fantasyLibrary.services;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fantasyLibrary.Constants.Cover;
import com.fantasyLibrary.Constants.Url;
import com.fantasyLibrary.Utils;
import com.fantasyLibrary.models.db.Author;
import com.fantasyLibrary.models.db.AuthorRoles;
import com.fantasyLibrary.models.db.Book;
import com.fantasyLibrary.models.db.Edition;
import com.fantasyLibrary.models.db.Series;
import com.fantasyLibrary.models.response.AuthorDetails;
import com.fantasyLibrary.models.response.BookDetails;
import com.fantasyLibrary.models.response.Response;
import com.fantasyLibrary.repos.AuthorRepository;
import com.fantasyLibrary.repos.AuthorRolesRepository;
import com.fantasyLibrary.repos.BookRepository;
import com.fantasyLibrary.repos.EditionRepository;
import com.fantasyLibrary.repos.SeriesRepository;

@Service
public class ClientService {
	
	@Autowired
	private BookRepository booksRepo;
	
	@Autowired
	private AuthorRepository authorRepo;
	
	@Autowired
	private EditionRepository editionRepo;
	
	@Autowired
	private SeriesRepository seriesRepo;
	
	@Autowired
	private AuthorRolesRepository authorRolesRepo;
	
	private Set<Long> setOfRoles = new HashSet<Long>();
	
	private Map<Long, Author> mapOfAuthors = new HashMap<Long, Author>();
	
	private Map<Long, String> mapOfRoles = new HashMap<Long, String>();

	public List<Response> getAllBooks() {
		Utils.printTime("Start");
		List<Series> series = (List<Series>) seriesRepo.findAll();
		List<Book> books = (List<Book>) booksRepo.findAll();
		
		mapOfAuthors = populateMapOfAuthors(books);
		mapOfRoles = populateMapOfRoles();
		
		List<BookDetails> booksWithDetails = books.stream()
				.map(b-> mapToBookDetails(b, series))
			.collect(toList());
		
		List<Response> responses = mapOfAuthors.entrySet().stream()
			.map(a-> populateResponse(booksWithDetails, a))
			.collect(toList());
		
		Utils.printTime("End");
		
		return responses;
	}	
	
	private Map<Long, Author> populateMapOfAuthors(List<Book> books) {
		return books.stream()
				.flatMap(s-> getAuthorCodes(s.getListOfAuthors()).stream())
				.distinct()
				.map(this::mapToAuthorDetails)
				.collect(toMap(Author::getGoodreadsId, a-> a));
	}
	
	private Map<Long, String> populateMapOfRoles() {
		List<AuthorRoles> roles = (List<AuthorRoles>) authorRolesRepo.findAll();
		
		return roles.stream()
				.collect(toMap(AuthorRoles::getId, s-> s.getRole().equals("")? "Author": s.getRole()));
	}

	private List<String> getAuthorCodes(String codes){
		String[] splitCodes = codes.split(", ");
		
		return IntStream.range(0, splitCodes.length)
			.mapToObj(i-> getAuthorCode(splitCodes[i]))
			.collect(toList());
	}
	
	private String getAuthorCode(String code) {
		return code.split("[|]")[0];
	}
	
	private Author mapToAuthorDetails(String authorCode) {
		return authorRepo.findById(Long.valueOf(getAuthorCode(authorCode))).orElse(new Author());
	}
	
	private String[] splitSequence(String sequence) {
		return sequence.split(", ");
	}
	
	private BookDetails mapToBookDetails(Book book, List<Series> series) {
		BookDetails details = new BookDetails();
		String[] sequence = splitSequence(book.getSequence());
		
		details.setAverageRating(book.getAverageRating());
		details.setDescription(book.getDescription());
		details.setId(book.getId());
		details.setRatingCount(book.getRatingsCount());
		details.setTextReviewsCount(book.getTextReviewsCount());
		details.setSequel(sequence[0]);
		details.setSubSequel(sequence[1]);
		details.setTitle(book.getTitle());
		details.setPublicationDate(book.getPublicationDate());
		details.setEditions(fetchEditions(book));
		details.setSeries(getSeries(book, series));
		details.setCovers(getCovers(book));
		details.setAuthors(book.getListOfAuthors());
		details.setBookUrl(Url.GR_BOOK_PAGE_PREFIX.value() + details.getEditions().get(0).getGoodreadsId());
		
		return details;
	}

	private List<com.fantasyLibrary.models.response.Edition> fetchEditions(Book book) {
		List<Edition> editions = editionRepo.findByBookId(book.getId());

		return editions.stream()
				.map(this::mapEditions)
				.collect(toList());
	}
	
	private com.fantasyLibrary.models.response.Edition mapEditions(Edition e){
		com.fantasyLibrary.models.response.Edition edition = 
				new com.fantasyLibrary.models.response.Edition();
		edition.setFormat(e.getFormat());
		edition.setGoodreadsId(e.getGoodreadsId());
		edition.setIsbn(e.getIsbn());
		edition.setNumOfPages(e.getNumOfPages());
		edition.setPublisher(e.getPublisher());
		
		return edition;
	}
	
	private List<com.fantasyLibrary.models.response.Series> getSeries(Book book, List<Series> series_) {
		String[] splitSeries = book.getListOfSeries().split(", ");
		 
		return IntStream.range(0, splitSeries.length)
					.filter(i-> series_.stream().anyMatch(s-> splitSeries[i].equals(s.getName())))
					.mapToObj(i-> createSeries(series_.get(i)))
					.collect(toList());
	}
	
	private com.fantasyLibrary.models.response.Series createSeries(Series s){
		return new com.fantasyLibrary.models.response.Series(s.getId(), s.getName());
	}
	
	private List<String> getCovers(Book book) {
		String[] covers = book.getCovers().split(", ");
		
		return IntStream.range(0, covers.length)
					.mapToObj(i-> createCover(covers[i]))
					.collect(toList());
	}
	
	private String createCover(String coverId) {
		return !coverId.contains("/")? Cover.OL_PREFIX.value() + coverId + Cover.OL_SUFIX.value():
					Cover.GR_PREFIX.value() + coverId + Cover.GR_SUFIX_CHANGE.value();
	}
	
	private Response populateResponse(List<BookDetails> books, Map.Entry<Long, Author> author) {
		setOfRoles.clear();
		Response response = new Response(); 
		List<BookDetails> booksWithDetails = getBooksPerAuthor(books, author.getValue());
		
		AuthorDetails details = populateAuthorDetails(booksWithDetails, author.getValue());
		response.setAuthor(details);
		response.setBooks(booksWithDetails);
		
		return response;
	}

	private List<BookDetails> getBooksPerAuthor(List<BookDetails> books, Author author) {
		return books.stream()
				.filter(b-> findAuthor(b.getAuthors(), author.getGoodreadsId()))
				.map(this::translateAuthorsCode)
				.collect(toList());
	}

	private boolean findAuthor(String authors, long author) {
		String[] splitAuthors = authors.split(", ");
		
		return IntStream.range(0, splitAuthors.length)
				.anyMatch(i -> getAuthorCode(splitAuthors[i]).equals(author+""));
	}

	private AuthorDetails populateAuthorDetails(List<BookDetails> books, Author author) {
		AuthorDetails authorDetails = new AuthorDetails();
		
		authorDetails.setFirtsName(author.getFirstName());
		authorDetails.setLastName(author.getLastName());
		authorDetails.setBornAt(author.getBornAt());
		authorDetails.setDiedAt(author.getDiedAt());
		authorDetails.setGoodreadsId(author.getGoodreadsId());
		authorDetails.setImageUrl(author.getImage().equals("")? "": Cover.GR_AUTHOR_PREFIX.value() + author.getImage());
		authorDetails.setGoodreadsUrl(Url.GR_AUTHOR_PAGE_PREFIX.value() + author.getGoodreadsId());
		authorDetails.setRatingsCount(author.getRatingsCount());
		authorDetails.setAverageRating(author.getAverageRating());
		authorDetails.setTextReviewsCount(author.getTextReviewsCount());
		authorDetails.setRoles(parseRoles(author.getListOfRoles()));
		return authorDetails;
	}
	
	private Set<String> parseRoles(String listOfRoles) {
		String[] splitRoles = listOfRoles.split(", ");
		
		return IntStream.range(0, splitRoles.length)
			.mapToObj(i-> mapOfRoles.get(Long.valueOf(splitRoles[i])))
			.collect(toSet());
	}
	
	private BookDetails translateAuthorsCode(BookDetails book) {
		String[] authorsWithRoles = book.getAuthors().split(", ");
		List<com.fantasyLibrary.models.response.Author> authors = new ArrayList<com.fantasyLibrary.models.response.Author>();
		for(String authorWithRoles: authorsWithRoles) {
			String[] split = authorWithRoles.split("[|]");
			Author details = mapOfAuthors.get(Long.valueOf(split[0]));
			String role = mapOfRoles.get(Long.valueOf(split[1]));
			String fullName = details.getFirstName() + " " + details.getLastName(); 
			authors.add(new com.fantasyLibrary.models.response.Author(fullName, role));
		}
		book.setListOfAuthors(authors);
		return book;
	}
}

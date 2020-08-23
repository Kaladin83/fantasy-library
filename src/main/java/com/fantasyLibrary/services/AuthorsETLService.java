package com.fantasyLibrary.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fantasyLibrary.Constants.Cover;
import com.fantasyLibrary.Constants.Ext;
import com.fantasyLibrary.Constants.Url;
import com.fantasyLibrary.Utils;
import com.fantasyLibrary.models.db.Author;
import com.fantasyLibrary.models.goodreads.AuthorBook;
import com.fantasyLibrary.models.goodreads.author.AuthorDetailed;
import com.fantasyLibrary.models.goodreads.search.GoodreadsSearchResult;
import com.fantasyLibrary.models.goodreads.showAuthor.ShowAuthor;
import com.fantasyLibrary.models.goodreads.showAuthor.ShowResponse;
import com.fantasyLibrary.repos.AuthorRepository;

@Service
public class AuthorsETLService {
	
	@Autowired
	private AuthorRepository authorRepo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public void extractAuthors() {
		File file = new File("src/main/resources/Authors.txt"); 
		Scanner scaner;
		Utils.printTime("Start Process Time");
		
		try {
			scaner = new Scanner(file);
			while (scaner.hasNextLine()) {
				getAuthorDetails(scaner.nextLine());
			}
			      
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		Utils.printTime("End Process Time");
	}

	private void getAuthorDetails(String name) {
		String[] authorNames = Utils.distructAuthorName(name);
		
		Long authorId = getAuthorId(authorNames[0], authorNames[1]);
		if (authorId > 0) {
			getAuthorDetailsById(new Author(authorNames[0], authorNames[1], authorId));
		}
	}

	private Long getAuthorId(String firstName, String lastName) {
		String authorFullName = (firstName.replaceAll(" ", "+") + "+" + lastName).toLowerCase();
		String url = Url.BASE_GOODREADS_SEARCH.value() + authorFullName
				+ Ext.GOODREADS_KEY.value() + Ext.FORMAT.value();
		GoodreadsSearchResult searchResponse = 
				Utils.getForObject(GoodreadsSearchResult.class, url, restTemplate);
		
		return searchResponse == null || searchResponse.getSearch() == null? 0L: 
			searchResponse.getSearch().getWork().stream()
				.map(s-> s.getBestBook().getAuthor().getId())
				.findFirst().orElse(0);
	}
	
	public void getAuthorDetailsById(Author author) {
		String url = Url.BASE_GOODREADS_SHOW_AUTHOR.value() + author.getGoodreadsId() + "?"
				+ Ext.GOODREADS_KEY.value() + Ext.FORMAT.value();
		ShowResponse authorShowResponse = Utils.getForObject(ShowResponse.class, url, restTemplate);
		
		transformAndLoadAuthors(author, authorShowResponse);
	}
	
	private void transformAndLoadAuthors(Author author, ShowResponse authorShowResponse) {
		if(authorShowResponse != null && authorShowResponse.getAuthor() != null) {
			String[] names = Utils.distructAuthorName(authorShowResponse.getAuthor().getName().strip());
			String image = !authorShowResponse.getAuthor().getLargeImageUrl().contains("nophoto")?authorShowResponse.getAuthor().getLargeImageUrl()
					.replace(Cover.GR_AUTHOR_PREFIX.value(), ""): authorShowResponse.getAuthor().getLargeImageUrl()
					.replace(Cover.GR_AUTHOR_PREFIX_NO_PHOTO.value(), "");
			if (image.substring(0,2).equals("f_") || image.substring(0,2).equals("m_") || 
					image.substring(0,2).equals("u_")) {
				image = image.substring(0,2);
			}
			author.setBornAt(authorShowResponse.getAuthor().getBornAt());
			author.setDiedAt(authorShowResponse.getAuthor().getDiedAt());
			author.setHometown(authorShowResponse.getAuthor().getHometown());
			author.setImage(image);
			author.setFirstName(names[0]);
			author.setLastName(names[1]);
			populatedataFromBook(author, authorShowResponse.getAuthor().getBooks().get(0));	
		}
		authorRepo.save(author);
	}

	private void populatedataFromBook(Author author, AuthorBook book) {
		AuthorDetailed details = book.getAuthors().stream()
			.filter(a-> a.getId().intValue() == author.getGoodreadsId().intValue())
			.findFirst().orElse(null);
		if (details != null) {
			author.setAverageRating(details.getAverageRating());
			author.setRatingsCount(details.getRatingsCount());
			author.setTextReviewsCount(details.getTextReviewsCount());
		}
	}	
}

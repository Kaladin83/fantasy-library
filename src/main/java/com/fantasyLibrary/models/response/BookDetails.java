package com.fantasyLibrary.models.response;

import java.util.List;

public class BookDetails {
	private Long id;
	private String title;
	private String sequel;
	private String subSequel;
	private String description;
	private String publicationDate;
	private List<String> isbns;
	private List<String> covers;
	private List<Series> series;
	private List<Edition> editions;
	private List<Author> ListOfAuthors;
	private List<Genre> ListOfGenres;
	private Float averageRating;
	private Integer ratingCount;
	private Integer textReviewsCount;
	private String bookUrl;
	private String authors;
	
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getSequel() {
		return sequel;
	}
	
	public void setSequel(String sequel) {
		this.sequel = sequel;
	}
	
	public String getSubSequel() {
		return subSequel;
	}
	public void setSubSequel(String subSequel) {
		this.subSequel = subSequel;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<Series> getSeries() {
		return series;
	}
	
	public void setSeries(List<Series> series) {
		this.series = series;
	}
	
	public List<String> getIsbns() {
		return isbns;
	}
	
	public void setIsbns(List<String> isbns) {
		this.isbns = isbns;
	}
	
	public List<String> getCovers() {
		return covers;
	}
	
	public void setCovers(List<String> covers) {
		this.covers = covers;
	}
	
	public List<Edition> getEditions() {
		return editions;
	}
	
	public void setEditions(List<Edition> editions) {
		this.editions = editions;
	}
	
	public Float getAverageRating() {
		return averageRating;
	}
	
	public void setAverageRating(Float averageRating) {
		this.averageRating = averageRating;
	}
	
	public Integer getRatingCount() {
		return ratingCount;
	}
	
	public void setRatingCount(Integer ratingCount) {
		this.ratingCount = ratingCount;
	}

	public String getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(String publicationDate) {
		this.publicationDate = publicationDate;
	}

	public String getAuthors() {
		return authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	public String getBookUrl() {
		return bookUrl;
	}

	public void setBookUrl(String bookUrl) {
		this.bookUrl = bookUrl;
	}

	public List<Author> getListOfAuthors() {
		return ListOfAuthors;
	}

	public void setListOfAuthors(List<Author> listOfAuthors) {
		ListOfAuthors = listOfAuthors;
	}

	public Integer getTextReviewsCount() {
		return textReviewsCount;
	}

	public void setTextReviewsCount(Integer textReviewsCount) {
		this.textReviewsCount = textReviewsCount;
	}

	public List<Genre> getListOfGenres() {
		return ListOfGenres;
	}

	public void setListOfGenres(List<Genre> listOfGenres) {
		ListOfGenres = listOfGenres;
	}
}

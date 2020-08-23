package com.fantasyLibrary.models.extracted;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fantasyLibrary.models.db.Edition;

public class ExtractedBook {
	private String title;
	
	private String publicationDate;
	
	private String description;

	private Integer ratingsCount;
	
	private Integer textReviewsCount;
	
	private Float averageRating;
	
	private List<Author> authors;
	
	private Map<Integer, String> series;

	private List<String> covers = new ArrayList<String>();
	
	private String sequence;
	
	private String subSequence;
	
	private Edition edition;
	
	private String seriesAndSequence;
	
	private String language;
	
	private String originalTitle;
	
	private List<String> genres;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(String publicationDate) {
		this.publicationDate = publicationDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getRatingsCount() {
		return ratingsCount;
	}

	public void setRatingsCount(Integer ratingsCount) {
		this.ratingsCount = ratingsCount;
	}

	public Float getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(Float averageRating) {
		this.averageRating = averageRating;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public Map<Integer, String> getSeries() {
		return series;
	}

	public void setSeries(Map<Integer, String> series) {
		this.series = series;
	}

	public List<String> getCovers() {
		return covers;
	}

	public void setCovers(List<String> covers) {
		this.covers.addAll(covers);
	}

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	public Edition getEdition() {
		return edition;
	}

	public void setEdition(Edition edition) {
		this.edition = edition;
	}

	public String getSeriesAndSequence() {
		return seriesAndSequence;
	}

	public void setSeriesAndSequence(String seriesAndSequence) {
		this.seriesAndSequence = seriesAndSequence;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getSubSequence() {
		return subSequence;
	}

	public void setSubSequence(String subSequence) {
		this.subSequence = subSequence;
	}

	public String getOriginalTitle() {
		return originalTitle;
	}

	public void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
	}

	public Integer getTextReviewsCount() {
		return textReviewsCount;
	}

	public void setTextReviewsCount(Integer textReviewsCount) {
		this.textReviewsCount = textReviewsCount;
	}

	public List<String> getGenres() {
		return genres;
	}

	public void setGenres(List<String> genres) {
		this.genres = genres;
	}
}

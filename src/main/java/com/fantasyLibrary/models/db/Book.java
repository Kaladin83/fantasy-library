package com.fantasyLibrary.models.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book {
	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "id", columnDefinition ="serial")
	private Long id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "publication_date")
	private String publicationDate;
	
	@Column(name = "description", columnDefinition="TEXT")
	private String description;
	
	@Column(name = "ratings_count")
	private Integer ratingsCount;
	
	@Column(name = "average_rating")
	private Float averageRating;
	
	@Column(name = "list_of_authors", length = 1000)
	private String listOfAuthors;
	
	@Column(name = "list_of_series", length = 400)
	private String listOfSeries;
	
	@Column(name = "sequence")
	private String sequence;
	
	@Column(name = "covers", length = 400)
	private String covers;
	
	@Column(name = "text_reviews_count")
	private Integer textReviewsCount;

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

	public String getListOfAuthors() {
		return listOfAuthors;
	}

	public void setListOfAuthors(String listOfAuthors) {
		this.listOfAuthors = listOfAuthors;
	}

	public String getListOfSeries() {
		return listOfSeries;
	}

	public void setListOfSeries(String listOfSeries) {
		this.listOfSeries = listOfSeries;
	}

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	public String getCovers() {
		return covers;
	}

	public void setCovers(String covers) {
		this.covers = covers;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getTextReviewsCount() {
		return textReviewsCount;
	}

	public void setTextReviewsCount(Integer textReviewsCount) {
		this.textReviewsCount = textReviewsCount;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", publicationDate=" + publicationDate + ", description="
				+ description + ", ratingsCount=" + ratingsCount + ", averageRating=" + averageRating
				+ ", listOfAuthors=" + listOfAuthors + ", listOfSeries=" + listOfSeries + ", sequence=" + sequence
				+ ", covers=" + covers + ", textReviewsCount=" + textReviewsCount + "]";
	}
}

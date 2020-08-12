package com.fantasyLibrary.models.goodreads.search;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlAccessorType(XmlAccessType.FIELD)
public class WorkObject {
	@XmlElement(name="id")
	private int id;
	
	@XmlElement(name="books_count")
	private Integer booksCount;
	
	@XmlElement(name="ratings_count")
	private Integer ratingsCount;
	
	@XmlElement(name="text_reviews_count")
	private Integer textReviewsCount;
	
	@XmlElement(name="original_publication_year")
	private Integer originalPublicationYear;
	
	@XmlElement(name="original_publication_month")
	private Integer originalPublicationMonth;
	
	@XmlElement(name="original_publication_day")
	private Integer originalPublicationDay;
	
	@XmlElement(name="average_rating")
	private Float averageRating;
	
	@XmlElement(name="best_book")
	private Book bestBook;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBooksCount() {
		return booksCount;
	}

	public void setBooksCount(int booksCount) {
		this.booksCount = booksCount;
	}

	public Integer getRatingsCount() {
		return ratingsCount;
	}

	public void setRatingsCount(Integer ratingsCount) {
		this.ratingsCount = ratingsCount;
	}

	public Integer getTextReviewsCount() {
		return textReviewsCount;
	}

	public void setTextReviewsCount(Integer textReviewsCount) {
		this.textReviewsCount = textReviewsCount;
	}

	public Integer getOriginalPublicationYear() {
		return originalPublicationYear;
	}

	public void setOriginalPublicationYear(Integer originalPublicationYear) {
		this.originalPublicationYear = originalPublicationYear;
	}

	public Integer getOriginalPublicationMonth() {
		return originalPublicationMonth;
	}

	public void setOriginalPublicationMonth(Integer originalPublicationMonth) {
		this.originalPublicationMonth = originalPublicationMonth;
	}

	public Integer getOriginalPublicationDay() {
		return originalPublicationDay;
	}

	public void setOriginalPublicationDay(Integer originalPublicationDay) {
		this.originalPublicationDay = originalPublicationDay;
	}

	public Float getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(Float averageRating) {
		this.averageRating = averageRating;
	}

	public Book getBestBook() {
		return bestBook;
	}

	public void setBestBook(Book bestBook) {
		this.bestBook = bestBook;
	}
}

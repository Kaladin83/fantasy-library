package com.fantasyLibrary.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "work")
public class WorkObject {
	@XmlElement(name="id")
	private int id;
	
	@XmlElement(name="books_count")
	private int booksCount;
	
	@XmlElement(name="ratings_count")
	private int ratingsCount;
	
	@XmlElement(name="text_reviews_count")
	private int textReviewsCount;
	
	@XmlElement(name="original_publication_year")
	private int originalPublicationYear;
	
	@XmlElement(name="original_publication_month")
	private int originalPublicationMonth;
	
	@XmlElement(name="original_publication_day")
	private int originalPublicationDay;
	
	@XmlElement(name="average_rating")
	private int averageRating;
	
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

	public int getRatingsCount() {
		return ratingsCount;
	}

	public void setRatingsCount(int ratingsCount) {
		this.ratingsCount = ratingsCount;
	}

	public int getTextReviewsCount() {
		return textReviewsCount;
	}

	public void setTextReviewsCount(int textReviewsCount) {
		this.textReviewsCount = textReviewsCount;
	}

	public int getOriginalPublicationYear() {
		return originalPublicationYear;
	}

	public void setOriginalPublicationYear(int originalPublicationYear) {
		this.originalPublicationYear = originalPublicationYear;
	}

	public int getOriginalPublicationMonth() {
		return originalPublicationMonth;
	}

	public void setOriginalPublicationMonth(int originalPublicationMonth) {
		this.originalPublicationMonth = originalPublicationMonth;
	}

	public int getOriginalPublicationDay() {
		return originalPublicationDay;
	}

	public void setOriginalPublicationDay(int originalPublicationDay) {
		this.originalPublicationDay = originalPublicationDay;
	}

	public int getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(int averageRating) {
		this.averageRating = averageRating;
	}

	public Book getBestBook() {
		return bestBook;
	}

	public void setBestBook(Book bestBook) {
		this.bestBook = bestBook;
	}
}

package com.fantasyLibrary.models.goodreads.showBook;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

@XmlAccessorType(XmlAccessType.FIELD)
public class SimilarBooks {

	@XmlElement(name="id")
	private Integer id;
	
	@XmlElement(name="uri")
	private String uri;
	
	@XmlElement(name="title")
	private String title;
	
	@XmlElement(name="link")
	private String link;
	
	@XmlElement(name="small_image_url")
	private String smallImageUrl;
	
	@XmlElement(name="image_url")
	private String imageUrl;
	
	@XmlElement(name="num_pages")
	private String numPages;
	
	@XmlElement(name="work")
	private ID work;
	
	@XmlElement(name="isbn")
	private String isbn;
	
	@XmlElement(name="isbn13")
	private String isbn13;
	
	@XmlElement(name="average_rating")
	private Float averageRating;
	
	@XmlElement(name="ratings_count")
	private Integer ratingsCount;
	
	@XmlElement(name="publication_year")
	private Integer publicationYear;
	
	@XmlElement(name="publication_month")
	private Integer publicationMonth;
	
	@XmlElement(name="publication_day")
	private Integer publicationDay;
	
	@XmlElementWrapper(name="authors")
	@XmlElement(name="author")
	private List<SimilarBooksAuthor> authors;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getSmallImageUrl() {
		return smallImageUrl;
	}

	public void setSmallImageUrl(String smallImageUrl) {
		this.smallImageUrl = smallImageUrl;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getNumPages() {
		return numPages;
	}

	public void setNumPages(String numPages) {
		this.numPages = numPages;
	}

	public ID getWork() {
		return work;
	}

	public void setWork(ID work) {
		this.work = work;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getIsbn13() {
		return isbn13;
	}

	public void setIsbn13(String isbn13) {
		this.isbn13 = isbn13;
	}

	public Float getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(Float averageRating) {
		this.averageRating = averageRating;
	}

	public Integer getRatingsCount() {
		return ratingsCount;
	}

	public void setRatingsCount(Integer ratingsCount) {
		this.ratingsCount = ratingsCount;
	}

	public Integer getPublicationYear() {
		return publicationYear;
	}

	public void setPublicationYear(Integer publicationYear) {
		this.publicationYear = publicationYear;
	}

	public Integer getPublicationMonth() {
		return publicationMonth;
	}

	public void setPublicationMonth(Integer publicationMonth) {
		this.publicationMonth = publicationMonth;
	}

	public Integer getPublicationDay() {
		return publicationDay;
	}

	public void setPublicationDay(Integer publicationDay) {
		this.publicationDay = publicationDay;
	}

	public List<SimilarBooksAuthor> getAuthors() {
		return authors;
	}

	public void setAuthors(List<SimilarBooksAuthor> authors) {
		this.authors = authors;
	}
}

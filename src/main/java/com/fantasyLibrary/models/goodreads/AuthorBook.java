package com.fantasyLibrary.models.goodreads;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import com.fantasyLibrary.models.goodreads.author.AuthorDetailed;

@XmlAccessorType(XmlAccessType.FIELD)
public class AuthorBook {
	@XmlElement(name="id")
	private Integer id;
	
	@XmlElement(name="isbn")
	private String isbn;
	
	@XmlElement(name="isbn13")
	private String isbn13;
	
	@XmlElement(name="text_reviews_count")
	private Integer textReviewsCount;
	
	@XmlElement(name="uri")
	private String uri;
	
	@XmlElement(name="title")
	private String title;
	
	@XmlElement(name="title_without_series")
	private String titleWithoutSeries;
	
	@XmlElement(name="image_url")
	private String imageUrl;
	
	@XmlElement(name="small_image_url")
	private String smallImageUrl;
	
	@XmlElement(name="large_image_url")
	private String largeImageUrl;
	
	@XmlElement(name="link")
	private String link;
	
	@XmlElement(name="num_pages")
	private Integer numPages;
	
	@XmlElement(name="format")
	private String format;
	
	@XmlElement(name="edition_information")
	private String editionInformation;
	
	@XmlElement(name="publisher")
	private String publisher;
	
	@XmlElement(name="publication_day")
	private Integer publicationDay;
	
	@XmlElement(name="publication_year")
	private Integer publicationYear;
	
	@XmlElement(name="publication_month")
	private Integer publicationMonth;
	
	@XmlElement(name="average_rating")
	private Float averageRating;
	
	@XmlElement(name="ratings_count")
	private Integer ratingsCount;
	
	@XmlElement(name="description")
	private String description;
	
	@XmlElementWrapper(name="authors")
	@XmlElement(name="author")
	private List<AuthorDetailed> authors;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Integer getTextReviewsCount() {
		return textReviewsCount;
	}

	public void setTextReviewsCount(Integer textReviewsCount) {
		this.textReviewsCount = textReviewsCount;
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

	public String getTitleWithoutSeries() {
		return titleWithoutSeries;
	}

	public void setTitleWithoutSeries(String titleWithoutSeries) {
		this.titleWithoutSeries = titleWithoutSeries;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getSmallImageUrl() {
		return smallImageUrl;
	}

	public void setSmallImageUrl(String smallImageUrl) {
		this.smallImageUrl = smallImageUrl;
	}

	public String getLargeImageUrl() {
		return largeImageUrl;
	}

	public void setLargeImageUrl(String largeImageUrl) {
		this.largeImageUrl = largeImageUrl;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Integer getNumPages() {
		return numPages;
	}

	public void setNumPages(Integer numPages) {
		this.numPages = numPages;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getEditionInformation() {
		return editionInformation;
	}

	public void setEditionInformation(String editionInformation) {
		this.editionInformation = editionInformation;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Integer getPublicationDay() {
		return publicationDay;
	}

	public void setPublicationDay(Integer publicationDay) {
		this.publicationDay = publicationDay;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<AuthorDetailed> getAuthors() {
		return authors;
	}

	public void setAuthors(List<AuthorDetailed> authors) {
		this.authors = authors;
	}
}

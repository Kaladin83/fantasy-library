package com.fantasyLibrary.models.goodreads.showBook;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

@XmlAccessorType(XmlAccessType.FIELD)
public class ShowBook {
	
	@XmlElement(name="id")
	private Integer id;
	
	@XmlElement(name="title")
	private String title;
	
	@XmlElement(name="isbn")
	private String isbn;
	
	@XmlElement(name="isbn13")
	private String isbn13;
	
	@XmlElement(name="asin")
	private String asin;
	
	@XmlElement(name="kindle_asin")
	private String kindleAsin;
	
	@XmlElement(name="marketplace_id")
	private String marketplaceId;
	
	@XmlElement(name="country_code")
	private String countryCode;
	
	@XmlElement(name="image_url")
	private String imageUrl;
	
	@XmlElement(name="small_image_url")
	private String smallImageUrl;
	
	@XmlElement(name="publication_year")
	private Integer publicationYear;
	
	@XmlElement(name="publication_month")
	private Integer publicationMonth;
	
	@XmlElement(name="publication_day")
	private Integer publicationDay;
	
	@XmlElement(name="publisher")
	private String publisher;
	
	@XmlElement(name="language_code")
	private String languageCode;
	
	@XmlElement(name="is_ebook")
	private boolean isEbook;
	
	@XmlElement(name="description")
	private String description;
	
	@XmlElement(name="work")
	private WorkObjectShow work;
	
	@XmlElement(name="average_rating")
	private Float averageRating;
	
	@XmlElement(name="num_pages")
	private String numPages;
	
	@XmlElement(name="format")
	private String format;
	
	@XmlElement(name="edition_information")
	private String editionInformation;
	
	@XmlElement(name="ratings_count")
	private Integer ratingsCount;
	
	@XmlElement(name="text_reviews_count")
	private Integer textReviewsCount;
	
	@XmlElement(name="url")
	private String url;
	
	@XmlElement(name="link")
	private String link;
	
	@XmlElementWrapper(name="authors")
	@XmlElement(name="author")
	private List<Author> authors;
	
	@XmlElement(name="reviews_widget")
	private String reviewsWidget;
	
	@XmlElementWrapper(name="popular_shelves")
	@XmlElement(name="shelf")
	private List<Shelf> popularShelves;
	
	@XmlElementWrapper(name="book_links")
	@XmlElement(name="book_link")
	private List<ShowLink> bookLinks;
	
	@XmlElementWrapper(name="buy_links")
	@XmlElement(name="buy_link")
	private List<ShowLink> buyLinks;
	
	@XmlElementWrapper(name="series_works")
	@XmlElement(name="series_work")
	private List<SeriesWork> seriesWorks;
	
	@XmlElementWrapper(name="similar_books")
	@XmlElement(name="book")
	private List<SimilarBooks> similarBooks;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getAsin() {
		return asin;
	}

	public void setAsin(String asin) {
		this.asin = asin;
	}

	public String getKindleAsin() {
		return kindleAsin;
	}

	public void setKindleAsin(String kindleAsin) {
		this.kindleAsin = kindleAsin;
	}

	public String getMarketplaceId() {
		return marketplaceId;
	}

	public void setMarketplaceId(String marketplaceId) {
		this.marketplaceId = marketplaceId;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
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

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	public boolean isEbook() {
		return isEbook;
	}

	public void setEbook(boolean isEbook) {
		this.isEbook = isEbook;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public WorkObjectShow getWork() {
		return work;
	}

	public void setWork(WorkObjectShow work) {
		this.work = work;
	}

	public Float getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(Float averageRating) {
		this.averageRating = averageRating;
	}

	public String getNumPages() {
		return numPages;
	}

	public void setNumPages(String numPages) {
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public String getReviewsWidget() {
		return reviewsWidget;
	}

	public void setReviewsWidget(String reviewsWidget) {
		this.reviewsWidget = reviewsWidget;
	}

	public List<Shelf> getPopularShelves() {
		return popularShelves;
	}

	public void setPopularShelves(List<Shelf> popularShelves) {
		this.popularShelves = popularShelves;
	}

	public List<ShowLink> getBookLinks() {
		return bookLinks;
	}

	public void setBookLinks(List<ShowLink> bookLinks) {
		this.bookLinks = bookLinks;
	}

	public List<ShowLink> getBuyLinks() {
		return buyLinks;
	}

	public void setBuyLinks(List<ShowLink> buyLinks) {
		this.buyLinks = buyLinks;
	}

	public List<SeriesWork> getSeriesWorks() {
		return seriesWorks;
	}

	public void setSeriesWorks(List<SeriesWork> seriesWorks) {
		this.seriesWorks = seriesWorks;
	}

	public List<SimilarBooks> getSimilarBooks() {
		return similarBooks;
	}

	public void setSimilarBooks(List<SimilarBooks> similarBooks) {
		this.similarBooks = similarBooks;
	}
}

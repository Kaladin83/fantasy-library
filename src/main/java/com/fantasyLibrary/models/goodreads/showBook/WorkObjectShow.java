package com.fantasyLibrary.models.goodreads.showBook;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class WorkObjectShow {

	@XmlElement(name="id")
	private int id;
	
	@XmlElement(name="books_count")
	private Integer booksCount;
	
	@XmlElement(name="best_book_id")
	private Integer bestBookId;
	
	@XmlElement(name="reviews_count")
	private Integer reviewsCount;
	
	@XmlElement(name="ratings_sum")
	private Integer ratingsSum;
	
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
	
	@XmlElement(name="original_title")
	private String originalTitle;
	
	@XmlElement(name="original_language_id")
	private Integer originalLanguageId;
	
	@XmlElement(name="media_type")
	private String mediaType;
	
	@XmlElement(name="rating_dist")
	private String rating_dist;
	
	@XmlElement(name="desc_user_id")
	private Integer descUserId;
	
	@XmlElement(name="default_chaptering_book_id")
	private Integer defaultChapteringBookId;
	
	@XmlElement(name="default_description_language_code")
	private String defaultDescriptionLanguageCode;
	
	@XmlElement(name="work_uri")
	private String workUri;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getBooksCount() {
		return booksCount;
	}

	public void setBooksCount(Integer booksCount) {
		this.booksCount = booksCount;
	}

	public Integer getBestBookId() {
		return bestBookId;
	}

	public void setBestBookId(Integer bestBookId) {
		this.bestBookId = bestBookId;
	}

	public Integer getReviewsCount() {
		return reviewsCount;
	}

	public void setReviewsCount(Integer reviewsCount) {
		this.reviewsCount = reviewsCount;
	}

	public Integer getRatingsSum() {
		return ratingsSum;
	}

	public void setRatingsSum(Integer ratingsSum) {
		this.ratingsSum = ratingsSum;
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

	public String getOriginalTitle() {
		return originalTitle;
	}

	public void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
	}

	public Integer getOriginalLanguageId() {
		return originalLanguageId;
	}

	public void setOriginalLanguageId(Integer originalLanguageId) {
		this.originalLanguageId = originalLanguageId;
	}

	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	public String getRating_dist() {
		return rating_dist;
	}

	public void setRating_dist(String rating_dist) {
		this.rating_dist = rating_dist;
	}

	public Integer getDescUserId() {
		return descUserId;
	}

	public void setDescUserId(Integer descUserId) {
		this.descUserId = descUserId;
	}

	public Integer getDefaultChapteringBookId() {
		return defaultChapteringBookId;
	}

	public void setDefaultChapteringBookId(Integer defaultChapteringBookId) {
		this.defaultChapteringBookId = defaultChapteringBookId;
	}

	public String getDefaultDescriptionLanguageCode() {
		return defaultDescriptionLanguageCode;
	}

	public void setDefaultDescriptionLanguageCode(String defaultDescriptionLanguageCode) {
		this.defaultDescriptionLanguageCode = defaultDescriptionLanguageCode;
	}

	public String getWorkUri() {
		return workUri;
	}

	public void setWorkUri(String workUri) {
		this.workUri = workUri;
	}
}

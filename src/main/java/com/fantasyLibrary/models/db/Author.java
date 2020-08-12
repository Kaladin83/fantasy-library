package com.fantasyLibrary.models.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Author {
	@Id
	@Column(name = "goodreads_id")
	private Long goodreadsId;
	
	@Column(name = "firstname")
	private String firstName;
	
	@Column(name = "lastname")
	private String lastName;
	
	@Column(name = "born")
	private String bornAt;
	
	@Column(name = "died")
	private String diedAt;
	
	@Column(name = "hometown")
	private String hometown;
	
	@Column(name = "image")
	private String image;
	
	@Column(name = "average_rating")
	private Float averageRating;
	
	@Column(name = "ratings_count")
	private Integer ratingsCount;
	
	@Column(name = "text_reviews_count")
	private Integer textReviewsCount;
	
	@Column(name = "list_of_roles")
	private String listOfRoles;
	
	public Author() {
	}

	public Author(String firstName, String lastName, Long goodreadsId) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.goodreadsId = goodreadsId;
	}

	public Long getGoodreadsId() {
		return goodreadsId;
	}

	public void setGoodreadsId(Long goodreadsId) {
		this.goodreadsId = goodreadsId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBornAt() {
		return bornAt;
	}

	public void setBornAt(String bornAt) {
		this.bornAt = bornAt;
	}

	public String getDiedAt() {
		return diedAt;
	}

	public void setDiedAt(String diedAt) {
		this.diedAt = diedAt;
	}

	public String getHometown() {
		return hometown;
	}

	public void setHometown(String hometown) {
		this.hometown = hometown;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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

	public Integer getTextReviewsCount() {
		return textReviewsCount;
	}

	public void setTextReviewsCount(Integer textReviewsCount) {
		this.textReviewsCount = textReviewsCount;
	}

	public String getListOfRoles() {
		return listOfRoles;
	}

	public void setListOfRoles(String listOfRoles) {
		this.listOfRoles = listOfRoles;
	}
}

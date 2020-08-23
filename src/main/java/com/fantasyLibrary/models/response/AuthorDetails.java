package com.fantasyLibrary.models.response;

import java.util.List;
import java.util.Set;

public class AuthorDetails {
	private String firstName;
	
	private String lastName;
	
	private String imageUrl;
	
	private Float averageRating;
	
	private Integer ratingsCount;
	
	private Integer textReviewsCount;
	
	private Long goodreadsId;
	
	private String bornAt;
	
	private String diedAt;
	
	private String goodreadsUrl;
	
	private Set<String> roles;
	
	private List<Genre> topGenres;

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

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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

	public Long getGoodreadsId() {
		return goodreadsId;
	}

	public void setGoodreadsId(Long goodreadsId) {
		this.goodreadsId = goodreadsId;
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

	public String getGoodreadsUrl() {
		return goodreadsUrl;
	}

	public void setGoodreadsUrl(String goodreadsUrl) {
		this.goodreadsUrl = goodreadsUrl;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	public Integer getTextReviewsCount() {
		return textReviewsCount;
	}

	public void setTextReviewsCount(Integer textReviewsCount) {
		this.textReviewsCount = textReviewsCount;
	}

	public List<Genre> getTopGenres() {
		return topGenres;
	}

	public void setTopGenres(List<Genre> topGenres) {
		this.topGenres = topGenres;
	}
}

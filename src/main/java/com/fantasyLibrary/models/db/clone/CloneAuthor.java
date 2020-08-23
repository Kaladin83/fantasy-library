package com.fantasyLibrary.models.db.clone;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fantasyLibrary.models.db.Author;

@Entity
@Table(name = "clone_author")
public class CloneAuthor {
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
	
	@Column(name = "top_genres")
	private String topGenres;
	
	public CloneAuthor() {
	}

	public CloneAuthor(String firstName, String lastName, Long goodreadsId) {
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

	public String getTopGenres() {
		return topGenres;
	}

	public void setTopGenres(String topGenres) {
		this.topGenres = topGenres;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((averageRating == null) ? 0 : averageRating.hashCode());
		result = prime * result + ((bornAt == null) ? 0 : bornAt.hashCode());
		result = prime * result + ((diedAt == null) ? 0 : diedAt.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((goodreadsId == null) ? 0 : goodreadsId.hashCode());
		result = prime * result + ((hometown == null) ? 0 : hometown.hashCode());
		result = prime * result + ((image == null) ? 0 : image.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((listOfRoles == null) ? 0 : listOfRoles.hashCode());
		result = prime * result + ((ratingsCount == null) ? 0 : ratingsCount.hashCode());
		result = prime * result + ((textReviewsCount == null) ? 0 : textReviewsCount.hashCode());
		result = prime * result + ((topGenres == null) ? 0 : topGenres.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CloneAuthor other = (CloneAuthor) obj;
		if (averageRating == null) {
			if (other.averageRating != null)
				return false;
		} else if (!averageRating.equals(other.averageRating))
			return false;
		if (bornAt == null) {
			if (other.bornAt != null)
				return false;
		} else if (!bornAt.equals(other.bornAt))
			return false;
		if (diedAt == null) {
			if (other.diedAt != null)
				return false;
		} else if (!diedAt.equals(other.diedAt))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (goodreadsId == null) {
			if (other.goodreadsId != null)
				return false;
		} else if (!goodreadsId.equals(other.goodreadsId))
			return false;
		if (hometown == null) {
			if (other.hometown != null)
				return false;
		} else if (!hometown.equals(other.hometown))
			return false;
		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (listOfRoles == null) {
			if (other.listOfRoles != null)
				return false;
		} else if (!listOfRoles.equals(other.listOfRoles))
			return false;
		if (ratingsCount == null) {
			if (other.ratingsCount != null)
				return false;
		} else if (!ratingsCount.equals(other.ratingsCount))
			return false;
		if (textReviewsCount == null) {
			if (other.textReviewsCount != null)
				return false;
		} else if (!textReviewsCount.equals(other.textReviewsCount))
			return false;
		if (topGenres == null) {
			if (other.topGenres != null)
				return false;
		} else if (!topGenres.equals(other.topGenres))
			return false;
		return true;
	}
}

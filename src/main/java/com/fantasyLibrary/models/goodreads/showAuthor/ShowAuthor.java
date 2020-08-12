package com.fantasyLibrary.models.goodreads.showAuthor;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlTransient;

import com.fantasyLibrary.models.goodreads.AuthorBook;

@XmlAccessorType(XmlAccessType.FIELD)
public class ShowAuthor {

	@XmlElement(name="id")
	private Integer id;
	
	@XmlElement(name="name")
	private String name;
	
	@XmlElement(name="link")
	private String link;
	
	@XmlElement(name="fans_count")
	private Integer fansCount;
	
	@XmlElement(name="author_followers_count")
	private String authorFollowersCount;
	
	@XmlElement(name="large_image_url")
	private String largeImageUrl;
	
	@XmlElement(name="image_url")
	private String imageUrl;
	
	@XmlElement(name="small_image_url")
	private String smallImageUrl;
	
	@XmlElement(name="about")
	private String about;
	
	@XmlTransient
	private String influences;
	
	@XmlElement(name="works_count")
	private Integer worksCount;
	
	@XmlElement(name="gender")
	private String gender;
	
	@XmlElement(name="hometown")
	private String hometown;
	
	@XmlElement(name="born_at")
	private String bornAt;
	
	@XmlElement(name="died_at")
	private String diedAt;
	
	@XmlTransient
	private String goodreadsAuthor;
	
	@XmlElementWrapper(name="books")
	@XmlElement(name="book")
	private List<AuthorBook> books;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Integer getFansCount() {
		return fansCount;
	}

	public void setFansCount(Integer fansCount) {
		this.fansCount = fansCount;
	}

	public String getAuthorFollowersCount() {
		return authorFollowersCount;
	}

	public void setAuthorFollowersCount(String authorFollowersCount) {
		this.authorFollowersCount = authorFollowersCount;
	}

	public String getLargeImageUrl() {
		return largeImageUrl;
	}

	public void setLargeImageUrl(String largeImageUrl) {
		this.largeImageUrl = largeImageUrl;
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

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getInfluences() {
		return influences;
	}

	public void setInfluences(String influences) {
		this.influences = influences;
	}

	public Integer getWorksCount() {
		return worksCount;
	}

	public void setWorksCount(Integer worksCount) {
		this.worksCount = worksCount;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getHometown() {
		return hometown;
	}

	public void setHometown(String hometown) {
		this.hometown = hometown;
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

	public String getGoodreadsAuthor() {
		return goodreadsAuthor;
	}

	public void setGoodreadsAuthor(String goodreadsAuthor) {
		this.goodreadsAuthor = goodreadsAuthor;
	}

	public List<AuthorBook> getBooks() {
		return books;
	}

	public void setBooks(List<AuthorBook> books) {
		this.books = books;
	}
}

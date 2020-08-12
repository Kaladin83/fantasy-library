package com.fantasyLibrary.models.goodreads.search;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Book {
	
	@XmlElement(name="id")
	private Integer id;
	
	@XmlElement(name="title")
	private String title;
	
	@XmlElement(name="author")
	private Author author;
	
	@XmlElement(name="image_url")
	private Integer imageUrl;
	
	@XmlElement(name="small_image_url")
	private String smallImageUrl;

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

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Integer getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(Integer imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getSmallImageUrl() {
		return smallImageUrl;
	}

	public void setSmallImageUrl(String smallImageUrl) {
		this.smallImageUrl = smallImageUrl;
	}
}

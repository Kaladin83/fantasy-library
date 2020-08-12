package com.fantasyLibrary.models.goodreads.author;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class AuthorSearch {
	@XmlElement
	private int id;
	
	@XmlElement
	private String name;
	
	@XmlElement
	private String link;
    
	@XmlElement
	private AuthorBooks books;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public AuthorBooks getAuthorsBooks() {
		return books;
	}

	public void setAuthorsBooks(AuthorBooks books) {
		this.books = books;
	}
	
}

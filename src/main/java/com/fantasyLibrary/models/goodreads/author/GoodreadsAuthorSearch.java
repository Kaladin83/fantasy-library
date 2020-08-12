package com.fantasyLibrary.models.goodreads.author;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fantasyLibrary.models.goodreads.SearchRequestResponse;

@XmlRootElement(name = "GoodreadsResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class GoodreadsAuthorSearch {
	@XmlElement(name="request")
	private SearchRequestResponse Request;
	@XmlElement(name="author")
	private AuthorSearch author;
	
	public SearchRequestResponse getRequest() {
		return Request;
	}
	public void setRequest(SearchRequestResponse request) {
		Request = request;
	}
	public AuthorSearch getAuthor() {
		return author;
	}
	public void setAuthor(AuthorSearch author) {
		this.author = author;
	}
}

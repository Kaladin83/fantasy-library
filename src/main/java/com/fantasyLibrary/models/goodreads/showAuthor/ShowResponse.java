package com.fantasyLibrary.models.goodreads.showAuthor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fantasyLibrary.models.goodreads.SearchRequestResponse;

@XmlRootElement(name = "GoodreadsResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class ShowResponse {
	
	@XmlElement(name="request")
	private SearchRequestResponse Request;
	
	@XmlElement(name="author")
	private ShowAuthor author;

	public SearchRequestResponse getRequest() {
		return Request;
	}

	public void setRequest(SearchRequestResponse request) {
		Request = request;
	}

	public ShowAuthor getAuthor() {
		return author;
	}

	public void setAuthor(ShowAuthor author) {
		this.author = author;
	}
}

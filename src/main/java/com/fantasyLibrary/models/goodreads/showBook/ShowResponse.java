package com.fantasyLibrary.models.goodreads.showBook;

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
	
	@XmlElement(name="book")
	private ShowBook book;

	public SearchRequestResponse getRequest() {
		return Request;
	}

	public void setRequest(SearchRequestResponse request) {
		Request = request;
	}

	public ShowBook getBook() {
		return book;
	}

	public void setBook(ShowBook book) {
		this.book = book;
	}

}

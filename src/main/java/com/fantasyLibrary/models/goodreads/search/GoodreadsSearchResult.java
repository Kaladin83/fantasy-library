package com.fantasyLibrary.models.goodreads.search;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fantasyLibrary.models.goodreads.SearchRequestResponse;
@XmlRootElement(name = "GoodreadsResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class GoodreadsSearchResult {
	@XmlElement(name="Request")
	private SearchRequestResponse request;
	@XmlElement(name="search")
	private SearchResponse search;
	
	public SearchRequestResponse getRequest() {
		return request;
	}
	public void setRequest(SearchRequestResponse request) {
		this.request = request;
	}
	public SearchResponse getSearch() {
		return search;
	}
	public void setSearch(SearchResponse search) {
		this.search = search;
	}
}

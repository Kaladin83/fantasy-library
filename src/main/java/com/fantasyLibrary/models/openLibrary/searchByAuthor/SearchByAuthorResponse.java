package com.fantasyLibrary.models.openLibrary.searchByAuthor;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchByAuthorResponse {
	
	@JsonProperty
	private Integer start;
	@JsonProperty
	private Integer num_found;
	@JsonProperty
	private Integer numFound;
	@JsonProperty
	private List<Document> docs;
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getNum_found() {
		return num_found;
	}
	public void setNum_found(Integer num_found) {
		this.num_found = num_found;
	}
	public Integer getNumFound() {
		return numFound;
	}
	public void setNumFound(Integer numFound) {
		this.numFound = numFound;
	}
	public List<Document> getDocs() {
		return docs;
	}
	public void setDocs(List<Document> docs) {
		this.docs = docs;
	}
}

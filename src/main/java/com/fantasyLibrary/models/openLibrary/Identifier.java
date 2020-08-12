package com.fantasyLibrary.models.openLibrary;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Identifier {

	@JsonProperty()
	private List<String> isbn_13;
	
	@JsonProperty()
	private List<String> lccn;
	
	@JsonProperty()
	private List<String> openlibrary;
	
	@JsonProperty()
	private List<String> isbn_10;
	
	@JsonProperty()
	private List<String> oclc;

	public List<String> getIsbn_13() {
		return isbn_13;
	}

	public void setIsbn_13(List<String> isbn_13) {
		this.isbn_13 = isbn_13;
	}

	public List<String> getLccn() {
		return lccn;
	}

	public void setLccn(List<String> lccn) {
		this.lccn = lccn;
	}

	public List<String> getOpenlibrary() {
		return openlibrary;
	}

	public void setOpenlibrary(List<String> openlibrary) {
		this.openlibrary = openlibrary;
	}

	public List<String> getIsbn_10() {
		return isbn_10;
	}

	public void setIsbn_10(List<String> isbn_10) {
		this.isbn_10 = isbn_10;
	}

	public List<String> getOclc() {
		return oclc;
	}

	public void setOclc(List<String> oclc) {
		this.oclc = oclc;
	}
}

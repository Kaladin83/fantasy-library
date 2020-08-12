package com.fantasyLibrary.models.openLibrary;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookNum {
	@JsonProperty()
	private String recordURL;
	
	@JsonProperty()
	private List<String> oclcs;
	
	@JsonProperty()
	private List<String> publishDates;
	
	@JsonProperty()
	private List<String> lccns;
	
	@JsonProperty()
	private OLBookDetails details;
	
	@JsonProperty()
	private List<String> isbns;
	
	@JsonProperty()
	private List<String> olids;
	
	@JsonProperty()
	private List<String> issns;
	
	@JsonProperty()
	private BookNumData data;

	public String getRecordURL() {
		return recordURL;
	}

	public void setRecordURL(String recordURL) {
		this.recordURL = recordURL;
	}

	public List<String> getOclcs() {
		return oclcs;
	}

	public void setOclcs(List<String> oclcs) {
		this.oclcs = oclcs;
	}

	public List<String> getPublishDates() {
		return publishDates;
	}

	public void setPublishDates(List<String> publishDates) {
		this.publishDates = publishDates;
	}

	public List<String> getLccns() {
		return lccns;
	}

	public void setLccns(List<String> lccns) {
		this.lccns = lccns;
	}

	public OLBookDetails getDetails() {
		return details;
	}

	public void setDetails(OLBookDetails details) {
		this.details = details;
	}

	public List<String> getIsbns() {
		return isbns;
	}

	public void setIsbns(List<String> isbns) {
		this.isbns = isbns;
	}

	public List<String> getOlids() {
		return olids;
	}

	public void setOlids(List<String> olids) {
		this.olids = olids;
	}

	public List<String> getIssns() {
		return issns;
	}

	public void setIssns(List<String> issns) {
		this.issns = issns;
	}

	public BookNumData getData() {
		return data;
	}

	public void setData(BookNumData data) {
		this.data = data;
	}
}

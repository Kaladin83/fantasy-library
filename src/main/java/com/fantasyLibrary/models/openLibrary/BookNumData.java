package com.fantasyLibrary.models.openLibrary;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookNumData {

	@JsonProperty("subject_people")
	private List<Subject> subjectPeople;
	
	@JsonProperty
	private String pagination;
	
	@JsonProperty("number_of_pages")
	private String numberOfPages;
	
	@JsonProperty
	private String subtitle;
	
	@JsonProperty
	private String title;
	
	@JsonProperty
	private String url;
	
	@JsonProperty
	private String notes;
	
	@JsonProperty
	private Identifier identifiers;

	@JsonProperty
	private List<Subject> subjects;
	
	@JsonProperty("publish_date")
	private String publishDate;
	
	@JsonProperty
	private String key;
	
	@JsonProperty
	private List<Subject> authors;
	
	@JsonProperty
	private Classification classifications;
	
	@JsonProperty("by_statement")
	private String byStatement;
	
	@JsonProperty
	private Cover cover;
	
//	@JsonProperty
//	private List<String> publishers;

	public List<Subject> getSubjectPeople() {
		return subjectPeople;
	}

	public void setSubjectPeople(List<Subject> subjectPeople) {
		this.subjectPeople = subjectPeople;
	}

	public String getPagination() {
		return pagination;
	}

	public void setPagination(String pagination) {
		this.pagination = pagination;
	}

	public String getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(String numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Identifier getIdentifiers() {
		return identifiers;
	}

	public void setIdentifiers(Identifier identifiers) {
		this.identifiers = identifiers;
	}

	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}

	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List<Subject> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Subject> authors) {
		this.authors = authors;
	}

	public Classification getClassifications() {
		return classifications;
	}

	public void setClassifications(Classification classifications) {
		this.classifications = classifications;
	}

	public String getByStatement() {
		return byStatement;
	}

	public void setByStatement(String byStatement) {
		this.byStatement = byStatement;
	}

	public Cover getCover() {
		return cover;
	}

	public void setCover(Cover cover) {
		this.cover = cover;
	}
	
	

//	public List<String> getPublishers() {
//		return publishers;
//	}
//
//	public void setPublishers(List<String> publishers) {
//		this.publishers = publishers;
//	}

}

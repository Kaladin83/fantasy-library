package com.fantasyLibrary.models.openLibrary.searchByAuthor;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Document {
	
	@JsonProperty("title_suggest")
	private String titleSuggest;
	
	@JsonProperty("edition_key")
	private List<String> editionKeys;
	
	@JsonProperty("cover_i")
	private Integer coverI;
	
	@JsonProperty("isbn")
	private List<String> isbns;
	
	@JsonProperty("has_fulltext")
	private Boolean hasFulltext;
	
	@JsonProperty("text")
	private List<String> texts;
	
	@JsonProperty("author_name")
	private List<String> authorNames;
	
	@JsonProperty("seed")
	private List<String> seeds;
	
	@JsonProperty("oclc")
	private List<String> oclcs;
	
	@JsonProperty("contributor")
	private List<String> contributors;
	
	@JsonProperty("author_key")
	private List<String> authorKeys;
   
	@JsonProperty
	private String title;
	
	@JsonProperty("publish_date")
	private List<String> publishDates;
    
	@JsonProperty
	private String type;
	
	@JsonProperty("ebook_count_i")
	private Integer ebookCountI;
	
	@JsonProperty("publish_place")
	private List<String> publishPlaces;
	
	@JsonProperty("edition_count")
	private Integer editionCount;

	@JsonProperty
	private String key;
	
	@JsonProperty("id_goodreads")
	private List<String> goodreadsIds;
	
	@JsonProperty("publisher")
	private List<String> publishers;
    
	@JsonProperty("language")
	private List<String> languages;
	
	@JsonProperty("last_modified_i")
	private Integer lastModifiedI;
  
	@JsonProperty("author_alternative_name")
	private List<String> authorAlternativeNames;
	
	@JsonProperty("cover_edition_key")
	private String coverEditionKey;
  
	@JsonProperty("first_sentence")
	private List<String> firstSentence;

	@JsonProperty("publish_year")
	private List<Integer> publishYear;
    
	@JsonProperty("first_publish_year")
	private Integer firstPublishYear;

	public String getTitleSuggest() {
		return titleSuggest;
	}

	public void setTitleSuggest(String titleSuggest) {
		this.titleSuggest = titleSuggest;
	}

	public List<String> getEditionKeys() {
		return editionKeys;
	}

	public void setEditionKeys(List<String> editionKeys) {
		this.editionKeys = editionKeys;
	}

	public Integer getCoverI() {
		return coverI;
	}

	public void setCoverI(Integer coverI) {
		this.coverI = coverI;
	}

	public List<String> getIsbns() {
		return isbns;
	}

	public void setIsbns(List<String> isbns) {
		this.isbns = isbns;
	}

	public Boolean getHasFulltext() {
		return hasFulltext;
	}

	public void setHasFulltext(Boolean hasFulltext) {
		this.hasFulltext = hasFulltext;
	}

	public List<String> getTexts() {
		return texts;
	}

	public void setTexts(List<String> texts) {
		this.texts = texts;
	}

	public List<String> getAuthorNames() {
		return authorNames;
	}

	public void setAuthorNames(List<String> authorNames) {
		this.authorNames = authorNames;
	}

	public List<String> getSeeds() {
		return seeds;
	}

	public void setSeeds(List<String> seeds) {
		this.seeds = seeds;
	}

	public List<String> getOclcs() {
		return oclcs;
	}

	public void setOclcs(List<String> oclcs) {
		this.oclcs = oclcs;
	}

	public List<String> getContributors() {
		return contributors;
	}

	public void setContributors(List<String> contributors) {
		this.contributors = contributors;
	}

	public List<String> getAuthorKeys() {
		return authorKeys;
	}

	public void setAuthorKeys(List<String> authorKeys) {
		this.authorKeys = authorKeys;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getPublishDates() {
		return publishDates;
	}

	public void setPublishDates(List<String> publishDates) {
		this.publishDates = publishDates;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getEbookCountI() {
		return ebookCountI;
	}

	public void setEbookCountI(Integer ebookCountI) {
		this.ebookCountI = ebookCountI;
	}

	public List<String> getPublishPlaces() {
		return publishPlaces;
	}

	public void setPublishPlaces(List<String> publishPlaces) {
		this.publishPlaces = publishPlaces;
	}

	public Integer getEditionCount() {
		return editionCount;
	}

	public void setEditionCount(Integer editionCount) {
		this.editionCount = editionCount;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List<String> getGoodreadsIds() {
		return goodreadsIds;
	}

	public void setGoodreadsIds(List<String> goodreadsIds) {
		this.goodreadsIds = goodreadsIds;
	}

	public List<String> getPublishers() {
		return publishers;
	}

	public void setPublishers(List<String> publishers) {
		this.publishers = publishers;
	}

	public List<String> getLanguages() {
		return languages;
	}

	public void setLanguages(List<String> languages) {
		this.languages = languages;
	}

	public Integer getLastModifiedI() {
		return lastModifiedI;
	}

	public void setLastModifiedI(Integer lastModifiedI) {
		this.lastModifiedI = lastModifiedI;
	}

	public List<String> getAuthorAlternativeNames() {
		return authorAlternativeNames;
	}

	public void setAuthorAlternativeNames(List<String> authorAlternativeNames) {
		this.authorAlternativeNames = authorAlternativeNames;
	}

	public String getCoverEditionKey() {
		return coverEditionKey;
	}

	public void setCoverEditionKey(String coverEditionKey) {
		this.coverEditionKey = coverEditionKey;
	}

	public List<String> getFirstSentence() {
		return firstSentence;
	}

	public void setFirstSentence(List<String> firstSentence) {
		this.firstSentence = firstSentence;
	}

	public List<Integer> getPublishYear() {
		return publishYear;
	}

	public void setPublishYear(List<Integer> publishYear) {
		this.publishYear = publishYear;
	}

	public Integer getFirstPublishYear() {
		return firstPublishYear;
	}

	public void setFirstPublishYear(Integer firstPublishYear) {
		this.firstPublishYear = firstPublishYear;
	}
}

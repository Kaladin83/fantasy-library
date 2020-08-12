package com.fantasyLibrary.models.openLibrary;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Details {
	
	@JsonProperty()
	private String subtitle;
	
	@JsonProperty()
	private List<Link> links;
	
	@JsonProperty()
	private List<String> series;
	
	@JsonProperty("local_id")
	private List<String> localId;
	
	@JsonProperty("lc_classifications")
	private List<String> lcClassifications;
	
	@JsonProperty("latest_revision")
	private Integer latestRevision;
	
	@JsonProperty
	private List<String> contributions;
	
	@JsonProperty("edition_name")
	private String editionName;
	
	@JsonProperty("source_records")
	private List<String> sourceRecords;
	
	@JsonProperty
	private String title;
	
	@JsonProperty("languages")
	private List<Key> languages;
	
	@JsonProperty
	private List<String> subjects;
	
	@JsonProperty("subject_people")
	private List<String> subjectPeople;
	
	@JsonProperty("publish_country")
	private String publishCountry;
	
	@JsonProperty("by_statement")
	private String byStatement;
	
	@JsonProperty("oclcCumbers")
	private String oclcNumbers;
	
	@JsonProperty("type")
	private Key type;
	
	@JsonProperty("revision")
	private Integer revision;
//	
//	@JsonProperty
//	private TypeValue description;
	
	@JsonProperty("full_title")
	private String fullTitle;
	
	@JsonProperty("last_modified")
	private TypeValue lastModified;
	
	@JsonProperty("key")
	private String key;
	
	@JsonProperty
	private List<NameKey> authors;
	
	@JsonProperty
	private String pagination;
	
	@JsonProperty
	private TypeValue created;
	
	@JsonProperty
	private List<String> iccn;
	
	@JsonProperty("number_of_pages")
	private Integer numberOfPages;
	
	@JsonProperty
	private List<String> isbn_13;
	
	@JsonProperty("dewey_decimal_class")
	private List<String> deweyDecimalClass;
	
	@JsonProperty
	private List<String> isbn_10;
	
	@JsonProperty("publish_date")
	private String publishDate;
	
	@JsonProperty
	private List<Key> works;
	
	@JsonProperty("table_of_contents")
	private List<Content> tableOfContents; 
	
	private List<Integer> covers;
	
	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

	public List<String> getSeries() {
		return series;
	}

	public void setSeries(List<String> series) {
		this.series = series;
	}
	
	public List<String> getLocalId() {
		return localId;
	}

	public void setLocalId(List<String> localId) {
		this.localId = localId;
	}

	public List<String> getLcClassifications() {
		return lcClassifications;
	}

	public void setLcClassifications(List<String> lcClassifications) {
		this.lcClassifications = lcClassifications;
	}

	public Integer getLatestRevision() {
		return latestRevision;
	}

	public void setLatestRevision(Integer latestRevision) {
		this.latestRevision = latestRevision;
	}

	public List<String> getContributions() {
		return contributions;
	}

	public void setContributions(List<String> contributions) {
		this.contributions = contributions;
	}

	public String getEditionName() {
		return editionName;
	}

	public void setEditionName(String editionName) {
		this.editionName = editionName;
	}

	public List<String> getSourceRecords() {
		return sourceRecords;
	}

	public void setSourceRecords(List<String> sourceRecords) {
		this.sourceRecords = sourceRecords;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Key> getLanguages() {
		return languages;
	}

	public void setLanguages(List<Key> languages) {
		this.languages = languages;
	}

	public List<String> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<String> subjects) {
		this.subjects = subjects;
	}

	public List<String> getSubjectPeople() {
		return subjectPeople;
	}

	public void setSubjectPeople(List<String> subjectPeople) {
		this.subjectPeople = subjectPeople;
	}

	public String getPublishCountry() {
		return publishCountry;
	}

	public void setPublishCountry(String publishCountry) {
		this.publishCountry = publishCountry;
	}

	public String getByStatement() {
		return byStatement;
	}

	public void setByStatement(String byStatement) {
		this.byStatement = byStatement;
	}

	public String getOclcNumbers() {
		return oclcNumbers;
	}

	public void setOclcNumbers(String oclcNumbers) {
		this.oclcNumbers = oclcNumbers;
	}

	public Key getType() {
		return type;
	}

	public void setType(Key type) {
		this.type = type;
	}

	public Integer getRevision() {
		return revision;
	}

	public void setRevision(Integer revision) {
		this.revision = revision;
	}

//	public TypeValue getDescription() {
//		return description;
//	}
//
//	public void setDescription(TypeValue description) {
//		this.description = description;
//	}

	public String getFullTitle() {
		return fullTitle;
	}

	public void setFullTitle(String fullTitle) {
		this.fullTitle = fullTitle;
	}

	public TypeValue getLastModified() {
		return lastModified;
	}

	public void setLastModified(TypeValue lastModified) {
		this.lastModified = lastModified;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List<NameKey> getAuthors() {
		return authors;
	}

	public void setAuthors(List<NameKey> authors) {
		this.authors = authors;
	}

	public String getPagination() {
		return pagination;
	}

	public void setPagination(String pagination) {
		this.pagination = pagination;
	}

	public TypeValue getCreated() {
		return created;
	}

	public void setCreated(TypeValue created) {
		this.created = created;
	}

	public List<String> getIccn() {
		return iccn;
	}

	public void setIccn(List<String> iccn) {
		this.iccn = iccn;
	}

	public Integer getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(Integer numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	public List<String> getIsbn_13() {
		return isbn_13;
	}

	public void setIsbn_13(List<String> isbn_13) {
		this.isbn_13 = isbn_13;
	}

	public List<String> getDeweyDecimalClass() {
		return deweyDecimalClass;
	}

	public void setDeweyDecimalClass(List<String> deweyDecimalClass) {
		this.deweyDecimalClass = deweyDecimalClass;
	}

	public List<String> getIsbn_10() {
		return isbn_10;
	}

	public void setIsbn_10(List<String> isbn_10) {
		this.isbn_10 = isbn_10;
	}

	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public List<Key> getWorks() {
		return works;
	}

	public void setWorks(List<Key> works) {
		this.works = works;
	}

	public List<Content> getTableOfContents() {
		return tableOfContents;
	}

	public void setTableOfContents(List<Content> tableOfContents) {
		this.tableOfContents = tableOfContents;
	}

	public List<Integer> getCovers() {
		return covers;
	}

	public void setCovers(List<Integer> covers) {
		this.covers = covers;
	}	
}

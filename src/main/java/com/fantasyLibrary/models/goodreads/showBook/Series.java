package com.fantasyLibrary.models.goodreads.showBook;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Series {
	@XmlElement(name="id")
	private Integer id;
	
	@XmlElement(name="title")
	private String title;
	
	@XmlElement(name="description")
	private Series description;
	
	@XmlElement(name="note")
	private String note;
	
	@XmlElement(name="series_works_count")
	private Integer seriesWorksCount;
	
	@XmlElement(name="primary_work_count")
	private Integer primaryWorkCount;
	
	@XmlElement(name="numbered")
	private boolean numbered;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Series getDescription() {
		return description;
	}

	public void setDescription(Series description) {
		this.description = description;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getSeriesWorksCount() {
		return seriesWorksCount;
	}

	public void setSeriesWorksCount(Integer seriesWorksCount) {
		this.seriesWorksCount = seriesWorksCount;
	}

	public Integer getPrimaryWorkCount() {
		return primaryWorkCount;
	}

	public void setPrimaryWorkCount(Integer primaryWorkCount) {
		this.primaryWorkCount = primaryWorkCount;
	}

	public boolean isNumbered() {
		return numbered;
	}

	public void setNumbered(boolean numbered) {
		this.numbered = numbered;
	}
}

package com.fantasyLibrary.models.openLibrary;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Content {
	@JsonProperty("title")
	private String title; 
	
	@JsonProperty("type")
	private Key type;
	
	@JsonProperty("level")
	private Integer level;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Key getType() {
		return type;
	}

	public void setType(Key type) {
		this.type = type;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
}

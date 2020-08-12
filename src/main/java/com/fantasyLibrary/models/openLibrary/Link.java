package com.fantasyLibrary.models.openLibrary;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Link {

	@JsonProperty()
	private String url;
	
	@JsonProperty()
	private String title;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}

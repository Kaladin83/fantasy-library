package com.fantasyLibrary.models.openLibrary;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NameKey {
	@JsonProperty
	private String name;
	
	@JsonProperty
	private String Key;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return Key;
	}

	public void setKey(String key) {
		Key = key;
	}
}

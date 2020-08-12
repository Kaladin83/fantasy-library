package com.fantasyLibrary.models.openLibrary;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Key {
	@JsonProperty
	private String key;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}

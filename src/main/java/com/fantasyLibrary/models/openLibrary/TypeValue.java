package com.fantasyLibrary.models.openLibrary;


import com.fasterxml.jackson.annotation.JsonProperty;

public class TypeValue {
	@JsonProperty
	private String type;
	
	@JsonProperty(required = false)
	private String value;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}

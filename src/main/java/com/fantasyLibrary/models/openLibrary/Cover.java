package com.fantasyLibrary.models.openLibrary;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Cover {
	@JsonProperty
	private String small;
	
	@JsonProperty
	private String large;
	
	@JsonProperty
	private String medium;

	public String getSmall() {
		return small;
	}

	public void setSmall(String small) {
		this.small = small;
	}

	public String getLarge() {
		return large;
	}

	public void setLarge(String large) {
		this.large = large;
	}

	public String getMedium() {
		return medium;
	}

	public void setMedium(String medium) {
		this.medium = medium;
	}
}

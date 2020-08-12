package com.fantasyLibrary.models.openLibrary;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenLibrary {
	private CoverRecord records;

	public CoverRecord getRecords() {
		return records;
	}

	public void setRecords(CoverRecord records) {
		this.records = records;
	}
}

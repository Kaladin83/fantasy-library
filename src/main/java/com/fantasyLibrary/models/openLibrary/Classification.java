package com.fantasyLibrary.models.openLibrary;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Classification {

	@JsonProperty("dewey_decimal_class")
	private List<String> deweyDecimalClass;
	
	@JsonProperty("lc_classifications")
	private List<String> lcClassifications;

	public List<String> getDeweyDecimalClass() {
		return deweyDecimalClass;
	}

	public void setDeweyDecimalClass(List<String> deweyDecimalClass) {
		this.deweyDecimalClass = deweyDecimalClass;
	}

	public List<String> getLcClassifications() {
		return lcClassifications;
	}

	public void setLcClassifications(List<String> lcClassifications) {
		this.lcClassifications = lcClassifications;
	} 
		
}

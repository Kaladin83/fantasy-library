package com.fantasyLibrary.models.extracted;

import java.util.List;

public class ExtractedCovers {
	private String title;
	
	private String sequence;
	
	private String subSequence;
	
	private List<String> isbns;
	
	private List<String> covers;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getIsbns() {
		return isbns;
	}

	public void setIsbns(List<String> isbns) {
		this.isbns = isbns;
	}

	public List<String> getCovers() {
		return covers;
	}

	public void setCovers(List<String> covers) {
		this.covers = covers;
	}

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	public String getSubSequence() {
		return subSequence;
	}

	public void setSubSequence(String subSequence) {
		this.subSequence = subSequence;
	}

	@Override
	public String toString() {
		return "title=" + title + ", sequence=" + sequence + ", subSequence=" + subSequence
				+ ", isbns=" + isbns + ", covers=" + covers;
	}
}

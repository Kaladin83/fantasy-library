package com.fantasyLibrary.models.goodreads.search;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
@XmlAccessorType(XmlAccessType.FIELD)
public class SearchResponse {
	@XmlElement(name="query")
	private String query;
	
	@XmlElement(name="results-start")
	private int resultsStart;
	
	@XmlElement(name="results-end")
	private int resultsEnd;
	
	@XmlElement(name="total-results")
	private int totalResults;
	
	@XmlElement(name="source")
	private String source;
	
	@XmlElement(name="query-time-seconds")
	private double queryTimeSeconds;
	
	@XmlElementWrapper(name="results")
	@XmlElement(name="work")
	private List<WorkObject> work;

	public List<WorkObject> getWork() {
		return work;
	}

	public void setWork(List<WorkObject> work) {
		this.work = work;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public int getResultsStart() {
		return resultsStart;
	}

	public void setResultsStart(int resultsStart) {
		this.resultsStart = resultsStart;
	}

	public int getResultsEnd() {
		return resultsEnd;
	}

	public void setResultsEnd(int resultsEnd) {
		this.resultsEnd = resultsEnd;
	}

	public int getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(int totalResults) {
		this.totalResults = totalResults;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public double getQueryTimeSeconds() {
		return queryTimeSeconds;
	}

	public void setQueryTimeSeconds(double queryTimeSeconds) {
		this.queryTimeSeconds = queryTimeSeconds;
	}

//	public SearchResults getResults() {
//		return results;
//	}
//
//	public void setResults(SearchResults results) {
//		this.results = results;
//	}
}

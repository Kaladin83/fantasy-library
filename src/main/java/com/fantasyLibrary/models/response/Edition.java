package com.fantasyLibrary.models.response;

public class Edition {
	private long goodreadsId;
	
	private String format;
	
	private String isbn;

	private String numOfPages;
	
	private String publisher;

	public long getGoodreadsId() {
		return goodreadsId;
	}

	public void setGoodreadsId(long goodreadsId) {
		this.goodreadsId = goodreadsId;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getNumOfPages() {
		return numOfPages;
	}

	public void setNumOfPages(String numOfPages) {
		this.numOfPages = numOfPages;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
}

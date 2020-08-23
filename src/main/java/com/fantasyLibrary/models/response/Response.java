package com.fantasyLibrary.models.response;

import java.util.List;

public class Response {
	private AuthorDetails info;
	
	private List<BookDetails> books;

	public AuthorDetails getInfo() {
		return info;
	}

	public void setInfo(AuthorDetails info) {
		this.info = info;
	}

	public List<BookDetails> getBooks() {
		return books;
	}

	public void setBooks(List<BookDetails> books) {
		this.books = books;
	}
}

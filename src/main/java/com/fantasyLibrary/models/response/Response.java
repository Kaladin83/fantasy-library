package com.fantasyLibrary.models.response;

import java.util.List;

public class Response {
	private AuthorDetails author;
	
	private List<BookDetails> books;

	public AuthorDetails getAuthor() {
		return author;
	}

	public void setAuthor(AuthorDetails author) {
		this.author = author;
	}

	public List<BookDetails> getBooks() {
		return books;
	}

	public void setBooks(List<BookDetails> books) {
		this.books = books;
	}
}

package com.fantasyLibrary.models.db.clone;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CloneEdition {
	@Id
	@Column(name = "goodreads_id")
	private long goodreadsId;
	
	@Column(name = "book_id")
	private long bookId;
	
	@Column
	private String format;
	
	@Column
	private String isbn;
	
	@Column(name = "num_of_pages")
	private String numOfPages;
	
	@Column
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

	public long getBookId() {
		return bookId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
	}

	@Override
	public String toString() {
		return "Edition [goodreadsId=" + goodreadsId + ", bookId=" + bookId + ", format=" + format + ", isbn=" + isbn
				+ ", numOfPages=" + numOfPages + ", publisher=" + publisher + "]";
	}
}

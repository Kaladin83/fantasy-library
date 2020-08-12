package com.fantasyLibrary.models.goodreads.author;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import com.fantasyLibrary.models.goodreads.AuthorBook;

@XmlAccessorType(XmlAccessType.FIELD)
public class AuthorBooks {
    
    @XmlElement(name="book")
    List<AuthorBook> books;
    
    @XmlAttribute
	private Integer start;

    @XmlAttribute
    private Integer end;
    
    @XmlAttribute
    private Integer total;
    
    public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getEnd() {
		return end;
	}

	public void setEnd(Integer end) {
		this.end = end;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
	public List<AuthorBook> getBooks() {
		return books;
	}

	public void setBooks(List<AuthorBook> books) {
		this.books = books;
	}
}

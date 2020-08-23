package com.fantasyLibrary.models.db;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Log {
	public Log(Long authorId, String status) {
		super();
		this.authorId = authorId;
		this.status = status;
		LocalDateTime localDate = LocalDateTime.now();
		dateEntered = localDate;
	}

	public Log() {
		
	}
	
	@Id
	@Column(name = "author_id")
	private Long authorId;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "date_entered", columnDefinition = "TIMESTAMP")
	private LocalDateTime dateEntered;

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getDateEntered() {
		return dateEntered;
	}

	public void setDateEntered(LocalDateTime dateEntered) {
		this.dateEntered = dateEntered;
	}
}

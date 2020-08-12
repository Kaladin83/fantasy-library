package com.fantasyLibrary.models.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Series {
	public Series(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Series() {
		
	}
	
	@Id
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

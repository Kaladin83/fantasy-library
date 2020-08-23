package com.fantasyLibrary.models.extracted;

public class Author {

	private Integer id;
	
	private String name;
	
	private long role;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getRole() {
		return role;
	}

	public void setRole(long role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Author [id=" + id + ", name=" + name + ", role=" + role + "]";
	}
}

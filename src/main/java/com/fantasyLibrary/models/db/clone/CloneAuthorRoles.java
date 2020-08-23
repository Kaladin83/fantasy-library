package com.fantasyLibrary.models.db.clone;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CloneAuthorRoles {
	public CloneAuthorRoles(String role) {
		this.role = role;
	}

	public CloneAuthorRoles() {
		
	}
	
	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "id", columnDefinition ="serial")
	private Long id;
	
	@Column(name = "role")
	private String role;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}

package com.projectHotel.PhanLam.entity;

import javax.persistence.Entity;

@Entity
public class Administration extends Person {
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}	
}

package com.projectHotel.PhanLam.entity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Administration extends Person {
	private String email;
	
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@OneToOne(mappedBy = "administration")
	private Account account;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}	
}

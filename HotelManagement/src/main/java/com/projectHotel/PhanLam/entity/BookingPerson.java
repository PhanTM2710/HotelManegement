package com.projectHotel.PhanLam.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class BookingPerson  extends Person{
	private String email;
	
	@OneToOne
	@JoinColumn(name = "card_id")
	private CreditCard card;

	@OneToMany(mappedBy = "person")
	private List<Booking> booking; 

	
	public List<Booking> getBooking() {
		return booking;
	}

	public void setBooking(List<Booking> booking) {
		this.booking = booking;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}	
	public CreditCard getCard() {
		return card;
	}

	public void setCard(CreditCard card) {
		this.card = card;
	}
}

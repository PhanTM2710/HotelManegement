package com.projectHotel.PhanLam.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String date;
	private long amount;
	private String desciption;
	private boolean isDelete;
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "card_id")
	private CreditCard card;
	
	@JsonBackReference
	@OneToOne
	@JoinColumn(name = "booking_id")
	private Booking booking;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public CreditCard getCard() {
		return card;
	}

	public void setCard(CreditCard card) {
		this.card = card;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}
	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public String getDesciption() {
		return desciption;
	}

	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}

	public Payment(String date, long amount, String desciption, CreditCard card, Booking booking,boolean isDelete) {
		this.date = date;
		this.amount = amount;
		this.desciption = desciption;
		this.card = card;
		this.booking = booking;
		this.isDelete=isDelete;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	public Payment(String date, long amount, CreditCard card, Booking booking) {
		this.date = date;
		this.amount = amount;
		this.card = card;
		this.booking = booking;
	}

	public Payment() {

	}

	public Payment(String date, long amount, String desciption, CreditCard card) {
		this.date = date;
		this.amount = amount;
		this.desciption = desciption;
		this.card = card;
	}

	public Payment(String date, long amount, CreditCard card) {
		this.date = date;
		this.amount = amount;
		this.card = card;
	}

	public boolean isisDelete() {
		return isDelete;
	}

	public void setisDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

		
}

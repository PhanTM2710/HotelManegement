package com.projectHotel.PhanLam.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class BookingDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int amountCustomer;
	
	@OneToOne
	@JoinColumn(name = "booking_id")
	private Booking booking;

	@ManyToMany
	@JoinTable(
	  name = "BookingDetail_mid", 
	  joinColumns = @JoinColumn(name = "bookingDetail_id"), 
	  inverseJoinColumns = @JoinColumn(name = "inroom_id"))
	private List<InRoom> customers;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAmountCustomer() {
		return amountCustomer;
	}

	public void setAmountCustomer(int amountCustomer) {
		this.amountCustomer = amountCustomer;
	}


	public List<InRoom> getCustomers() {
		return customers;
	}

	public void setCustomers(List<InRoom> customers) {
		this.customers = customers;
	}
	
	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}
}

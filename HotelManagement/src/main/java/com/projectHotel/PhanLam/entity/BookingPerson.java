package com.projectHotel.PhanLam.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class BookingPerson  extends Person{
	private String email;
	

	@JsonManagedReference
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

	public BookingPerson() {
		super();		
	}


	public BookingPerson(String name, String phone,String email) {
		super(name, phone);
		this.email = email;
	}

	public BookingPerson(String name, String phone, String address, String birthday, String email) {
		super(name, phone, address, birthday);
		this.email = email;
	}

	public BookingPerson(String email, List<Booking> booking) {
		super();
		this.email = email;
		this.booking = booking;
	}

	public BookingPerson(String name, String phone, String address, String birthday, String email,
			List<Booking> booking) {
		super(name, phone, address, birthday);
		this.email = email;
		this.booking = booking;
	}

	public BookingPerson(String name, String phone, String email, List<Booking> booking) {
		super(name, phone);
		this.email = email;
		this.booking = booking;
	}

	public BookingPerson(String name, String phone, String birthday, String email, List<Booking> booking) {
		super(name, phone, birthday);
		this.email = email;
		this.booking = booking;
	}
	
}

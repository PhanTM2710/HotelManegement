package com.projectHotel.PhanLam.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class BookingDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private boolean isDelete;
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "booking_id")
	private Booking booking;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "room_id")
	private Room room;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "bookingDetail")
	private List<InRoom> customers;
	
	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public List<InRoom> getCustomers() {
		return customers;
	}

	public void setCustomers(List<InRoom> customers) {
		this.customers = customers;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public BookingDetail(Room room) {
		this.room = room;
	}

	public BookingDetail() {
	}

	public BookingDetail(Booking booking) {

		this.booking = booking;
	}

	public BookingDetail(List<InRoom> customers) {

		this.customers = customers;
	}

	public BookingDetail(Booking booking, Room room) {
		this.booking = booking;
		this.room = room;
	}
		
	public BookingDetail(boolean isDelete, Booking booking, Room room) {
		this.isDelete = isDelete;
		this.booking = booking;
		this.room = room;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	@Override
	public String toString() {
		return "BookingDetail [id=" + id + ", booking=" + booking + ", room=" + room + ", customers=" + customers
				+ ", getBooking()=" + getBooking() + ", getCustomers()=" + getCustomers() + ", getId()=" + getId()
				+ ", getRoom()=" + getRoom() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

	public boolean isisDelete() {
		return isDelete;
	}

	public void setisDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}
	
}

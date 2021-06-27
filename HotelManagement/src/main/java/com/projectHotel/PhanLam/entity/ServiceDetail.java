package com.projectHotel.PhanLam.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class ServiceDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private boolean isDelete;
	private int quantity;
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "booking_id")
	private Booking booking;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "service_id")
	private Service service;

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ServiceDetail(Booking booking, Service service) {

		this.booking = booking;
		this.service = service;
	}
	

	public ServiceDetail(boolean isDelete, Booking booking, Service service) {
		this.isDelete = isDelete;
		this.booking = booking;
		this.service = service;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	public ServiceDetail() {

	}

	public boolean isisDelete() {
		return isDelete;
	}

	public void setisDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	
}

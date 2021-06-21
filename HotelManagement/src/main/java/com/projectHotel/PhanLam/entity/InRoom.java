package com.projectHotel.PhanLam.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class InRoom extends Person{
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "bookingdetail_id")
	private BookingDetail bookingDetail;
	
	public BookingDetail getBookingDetail() {
		return bookingDetail;
	}

	public void setBookingDetail(BookingDetail bookingDetail) {
		this.bookingDetail = bookingDetail;
	}
	
}

package com.projectHotel.PhanLam.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class InRoom extends Person{
	
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

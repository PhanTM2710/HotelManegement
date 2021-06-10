package com.projectHotel.PhanLam.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class InRoom extends Person{
	
	@ManyToMany(mappedBy = "customers")
	private List<BookingDetail> bookingDetail;

	public List<BookingDetail> getBookingDetail() {
		return bookingDetail;
	}

	public void setBookingDetail(List<BookingDetail> bookingDetail) {
		this.bookingDetail = bookingDetail;
	}
}

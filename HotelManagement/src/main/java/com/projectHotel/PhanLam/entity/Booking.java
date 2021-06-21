package com.projectHotel.PhanLam.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Booking implements Serializable{
	@Id
	private String id;	
	private String startDate;
	private String endDate;
	private long amount;
	
	@JsonManagedReference
	@OneToOne(mappedBy = "booking")
	private Payment payment;

	@JsonManagedReference
	@OneToMany(mappedBy = "booking")
	private List<BookingDetail> bookingDetail;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "bookingperson_id")
	private BookingPerson person;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "promotion_id")
	private Promotion promotion;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "booking")
	private List<ServiceDetail> serviceDetails;
			
	public List<ServiceDetail> getServiceDetails() {
		return serviceDetails;
	}

	public void setServiceDetails(List<ServiceDetail> serviceDetails) {
		this.serviceDetails = serviceDetails;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public List<BookingDetail> getBookingDetail() {
		return bookingDetail;
	}

	public void setBookingDetail(List<BookingDetail> bookingDetail) {
		this.bookingDetail = bookingDetail;
	}

	public BookingPerson getPerson() {
		return person;
	}

	public void setPerson(BookingPerson person) {
		this.person = person;
	}

	public Promotion getPromotion() {
		return promotion;
	}

	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	
}

package com.projectHotel.PhanLam.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Booking {
	@Id
	private String id;
	
//	@Temporal(TemporalType.DATE)
	private String startDate;
	
//	@Temporal(TemporalType.DATE)
	private String endDate;
	private double amount;
	
	@ManyToMany
	@JoinTable(
	  name = "Booking_middle", 
	  joinColumns = @JoinColumn(name = "Service_id"), 
	  inverseJoinColumns = @JoinColumn(name = "booking_id"))
	private List<Service> service;
	
	@OneToOne(mappedBy = "booking")
	private BookingDetail bookingDetail;
	

	@ManyToOne
	@JoinColumn(name = "bookingperson_id")
	private BookingPerson person;
	
	@ManyToOne
	@JoinColumn(name = "promotion_id")
	private Promotion promotion;
	
	@ManyToMany
	@JoinTable(
	  name = "Booking_mid", 
	  joinColumns = @JoinColumn(name = "room_id"), 
	  inverseJoinColumns = @JoinColumn(name = "booking_id"))
	private List<Room> rooms;
	
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
	public List<Room> getRooms() {
		return rooms;
	}
	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<Service> getService() {
		return service;
	}
	public void setService(List<Service> service) {
		this.service = service;
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
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public BookingDetail getBookingDetail() {
		return bookingDetail;
	}
	public void setBookingDetail(BookingDetail bookingDetail) {
		this.bookingDetail = bookingDetail;
	}
}

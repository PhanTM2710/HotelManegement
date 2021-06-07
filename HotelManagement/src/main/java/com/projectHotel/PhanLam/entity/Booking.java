package com.projectHotel.PhanLam.entity;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Booking {
	@Id
	private String bookingId;
	private String discount;
	private Date startDate;
	private Date endDate;
	private double amount;
	
	@ManyToOne
	@JoinColumn(name = "bookingperson_id")
	private BookingPerson person;
	
	@OneToOne
	@JoinColumn(name = "promotion_id")
	private Promotion promotion;
	
	@OneToMany(mappedBy = "booking")
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

	public String getBookingId() {
		return bookingId;
	}
	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}	
}

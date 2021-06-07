package com.projectHotel.PhanLam.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int roomId;
	private String roomNumber;
	private double price;
	private String type;
	private String description;
	private String roomCapacity;

	
	public String getRoomCapacity() {
		return roomCapacity;
	}

	public void setRoomCapacity(String roomCapacity) {
		this.roomCapacity = roomCapacity;
	}

	@ManyToOne
	@JoinColumn(name = "booking_id")
	private Booking booking;

	@OneToMany(mappedBy = "room", fetch = FetchType.EAGER)
	private List<Image> image;

	@ManyToOne
	@JoinColumn(name = "typeRoom_id")
	private TypeRoom typeRoom;

	public int getRoomId() {
		return roomId;
	}

	public TypeRoom getTypeRoom() {
		return typeRoom;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setTypeRoom(TypeRoom typeRoom) {
		this.typeRoom = typeRoom;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}


	public List<Image> getImage() {
		return image;
	}

	public void setImage(List<Image> image) {
		this.image = image;
	}
	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}
}

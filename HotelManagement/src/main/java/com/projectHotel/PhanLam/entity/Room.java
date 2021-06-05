package com.projectHotel.PhanLam.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Room {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int roomId;
	private String roomNumber;
	private double price;
	private String type;
	private String size;
	private String description;
	private String image;
	
	@ManyToOne
	@JoinColumn(name="typeRoom_id")
	private TypeRoom typeRoom;
	
	public int getRoomId() {
		return roomId;
	}
	public TypeRoom getTypeRoom() {
		return typeRoom;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}
	
	
}

package com.projectHotel.PhanLam.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Image {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String img;
		
	@ManyToOne
	@JoinColumn(name="room_id")
	private Room room;	
	

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
		
}

package com.projectHotel.PhanLam.entity;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Image {
	private int imageId;
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

	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
		
}

package com.projectHotel.PhanLam.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class TypeRoom {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int typeRoomId;
	private String type;
	private String size;
	private String image;
	
	@OneToMany(mappedBy="typeRoom")
	private List<Room> room;	
	
	public List<Room> getRoom() {
		return room;
	}
	public void setRoom(List<Room> room) {
		this.room = room;
	}
	public int getTypeRoomId() {
		return typeRoomId;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public void setTypeRoomId(int typeRoomId) {
		this.typeRoomId = typeRoomId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	
}

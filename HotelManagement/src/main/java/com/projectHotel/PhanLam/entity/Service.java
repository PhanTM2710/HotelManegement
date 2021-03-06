package com.projectHotel.PhanLam.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Service {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private long price;
	private String desciption;
	private String unit;
	private String image;
	private boolean isDelete;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "service")
	private List<ServiceDetail> serviceDetail;
	
	public String getDesciption() {
		return desciption;
	}
	public List<ServiceDetail> getServiceDetail() {
		return serviceDetail;
	}
	public void setServiceDetail(List<ServiceDetail> serviceDetail) {
		this.serviceDetail = serviceDetail;
	}
	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public boolean isisDelete() {
		return isDelete;
	}
	public void setisDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}
	
}

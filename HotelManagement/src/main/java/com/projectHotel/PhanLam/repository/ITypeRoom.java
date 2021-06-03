package com.projectHotel.PhanLam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.projectHotel.PhanLam.entity.TypeRoom;

@Repository
public interface ITypeRoom extends JpaRepository<TypeRoom, Integer>{
	
}

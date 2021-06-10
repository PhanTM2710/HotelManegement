package com.projectHotel.PhanLam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projectHotel.PhanLam.entity.Booking;

@Repository
public interface IBooking extends JpaRepository<Booking, String> {

}

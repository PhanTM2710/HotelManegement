package com.projectHotel.PhanLam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectHotel.PhanLam.entity.BookingPerson;

public interface IBookingPerson extends JpaRepository<BookingPerson, Integer> {

}

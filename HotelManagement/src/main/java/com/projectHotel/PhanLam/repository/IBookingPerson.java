package com.projectHotel.PhanLam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projectHotel.PhanLam.entity.BookingPerson;
@Repository
public interface IBookingPerson extends JpaRepository<BookingPerson, Integer> {

}

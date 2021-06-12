package com.projectHotel.PhanLam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectHotel.PhanLam.entity.BookingDetail;

public interface IBookingDetail extends JpaRepository<BookingDetail, Integer> {

}

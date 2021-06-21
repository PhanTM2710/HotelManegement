package com.projectHotel.PhanLam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projectHotel.PhanLam.entity.BookingDetail;
@Repository
public interface IBookingDetail extends JpaRepository<BookingDetail, Integer> {

}

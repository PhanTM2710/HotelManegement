package com.projectHotel.PhanLam.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projectHotel.PhanLam.entity.Room;

@Repository
public interface IRoom extends JpaRepository<Room, Integer> {

	@Query("SELECT r.* FROM room r "
			+ "LEFT JOIN booking_mid bm ON r.id = bm.room_id "
			+ "LEFT JOIN booking b ON b.id = bm.booking_id "
			+ "WHERE bm.room_id IS NULL "
			+ "OR b.start_date IS NULL "
			+ "OR b.end_date IS NULL "
			+ "OR b.end_date < ?1 OR b.start_date > ?2")
	List<Room> findByBooking_startDateAndBooking_endDate(String startDate, String endDate);
}

package com.projectHotel.PhanLam.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projectHotel.PhanLam.entity.Room;

@Repository
public interface IRoom extends JpaRepository<Room, Integer> {

	@Query(value = "SELECT r.* FROM room r "
			+ "LEFT JOIN booking_detail bd ON r.id = bd.room_id "
			+ "LEFT JOIN booking b ON b.id = bd.booking_id "
			+ "WHERE bd.room_id IS NULL "
			+ "OR b.start_date IS NULL "
			+ "OR b.end_date IS NULL "
			+ "OR b.end_date < ?1 OR b.start_date > ?2", nativeQuery = true)
	List<Room> filterRoomAvailable(String startDate, String endDate);
}

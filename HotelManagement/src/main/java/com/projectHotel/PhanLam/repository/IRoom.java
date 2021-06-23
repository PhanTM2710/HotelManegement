package com.projectHotel.PhanLam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projectHotel.PhanLam.entity.Room;

@Repository
public interface IRoom extends JpaRepository<Room, Integer> {

	@Query(value = "SELECT r.* \r\n"
			+ "FROM room r LEFT JOIN\r\n"
			+ "			(SELECT DISTINCT bd.room_id\r\n"
			+ "			FROM booking b JOIN booking_detail bd  ON b.id = bd.booking_id\r\n"
			+ "			WHERE (b.is_delete = 0) AND\r\n"
			+ "					(\r\n"
			+ "						b.start_date BETWEEN ?1 AND ?2 OR\r\n"
			+ "						b.end_date BETWEEN ?1 AND ?2\r\n"
			+ "					)) AS roomNotAvailable\r\n"
			+ "			ON r.id = roomNotAvailable.room_id\r\n"
			+ "WHERE r.is_delete = 0 AND roomNotAvailable.room_id IS NULL;", nativeQuery = true)
	List<Room> filterRoomAvailable(String startDate, String endDate);
}

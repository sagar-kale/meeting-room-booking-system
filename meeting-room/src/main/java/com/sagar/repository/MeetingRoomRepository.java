package com.sagar.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sagar.entity.*;

@Repository
public interface MeetingRoomRepository extends JpaRepository<MeetingRoom, Integer>{
	
	@Query(value = "select * from meeting_room as ro " 
			+ "where ro.id not in " 
			+ "("
			+ "select re.room_id "
			+ "from reservations as re " 
			+ "where (date_begin >= ?1 and date_begin < ?2) "
			+ "or (date_end >= ?1 and date_end < ?2) "
     		+ ")", nativeQuery = true)
	List<MeetingRoom> findMeetingRoomAvailable(Date db, Date de);
	
	@Query(value = "select * from (select * from meeting_room as ro where ro.id not in " 
	+ "(select re.room_id from reservations as re where (date_begin >= ?1 and date_begin < ?2) " 
	+ "or (date_end >= ?1 and date_end < ?2))) as roo where roo.id = ?3", nativeQuery = true)
	MeetingRoom checkAvailability(Date db, Date de, int id);

}

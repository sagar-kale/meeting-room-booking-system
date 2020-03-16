package com.sagar.repository;

import com.sagar.entity.Reservations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public interface ReservationsRepository extends JpaRepository<Reservations, Integer> {

    @Query(value = "SELECT * FROM reservations WHERE room_id = (select id from meeting_room where name = ?1)", nativeQuery = true)
    List<Reservations> findByName(String name);

    @Query("select r from Reservations r where r.dateEnd >=:date order by createdAt desc")
    LinkedList<Reservations> findAllByDate(Date date);

}

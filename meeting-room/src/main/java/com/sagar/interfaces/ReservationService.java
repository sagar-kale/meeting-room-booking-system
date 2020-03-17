package com.sagar.interfaces;

import com.sagar.entity.Reservations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public interface ReservationService {
    Reservations createReservation(Reservations reservation);

    List<Reservations> getReservations();

    Reservations findById(Integer id);

    Boolean deleteReservationsById(Integer id);

    List<Reservations> findByName(String name);

    List<Reservations> getFutureBookings();
}

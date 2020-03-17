package com.sagar.meetingroom;

import com.sagar.entity.MeetingRoom;
import com.sagar.entity.Reservations;
import com.sagar.entity.Response;
import com.sagar.interfaces.MeetingRoomService;
import com.sagar.interfaces.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservation")
public class ReservationsController {

    @Autowired
    private ReservationService reservationServiceImp;

    @Autowired
    private MeetingRoomService meetingRoomServiceImp;


    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = "application/json")
    public List<Reservations> getAllReservations() {
        List<Reservations> rooms = reservationServiceImp.getReservations();
        rooms.forEach(reservations -> {
            MeetingRoom byId = meetingRoomServiceImp.findById(reservations.getRoomId());
            reservations.setName(byId.getName());
            reservations.setCapacity(byId.getCapacity());
        });
        return rooms;

    }

    @GetMapping(produces = "application/json")
    public List<Reservations> getFutureReservations() {
        List<Reservations> rooms = reservationServiceImp.getFutureBookings();
        rooms.forEach(reservations -> {
            MeetingRoom byId = meetingRoomServiceImp.findById(reservations.getRoomId());
            reservations.setName(byId.getName());
            reservations.setCapacity(byId.getCapacity());
        });
        return rooms;

    }

    @RequestMapping(value = "/get/{name}", method = RequestMethod.GET, produces = "application/json")
    public List<Reservations> getAllReservationsByName(@PathVariable("name") String name) {
        List<Reservations> rooms = reservationServiceImp.findByName(name);
        return rooms;
    }


    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Reservations> getUserById(@PathVariable("id") Integer id) {
        System.out.println("Fetching Reservation with id " + id);
        Reservations reservation = reservationServiceImp.findById(id);
        if (reservation == null) {
            return new ResponseEntity<Reservations>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Reservations>(reservation, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", headers = "Accept=application/json")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Integer id) {
        Reservations reservation = reservationServiceImp.findById(id);
        if (reservation == null) {
            return new ResponseEntity<String>("No such Reservation", HttpStatus.NOT_FOUND);
        }
        reservationServiceImp.deleteReservationsById(id);
        return new ResponseEntity<String>("Reservation Deleted", HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "/book", headers = "Accept=application/json")
    public ResponseEntity<Response> createReservation(@RequestBody Reservations reservation) {
        System.out.println("Creating Reservation " + reservation);
        Reservations r = reservationServiceImp.createReservation(reservation);
        if (r != null) {
            return new ResponseEntity<>(new Response("Booking Created Successfully", HttpStatus.CREATED.value()), HttpStatus.CREATED);
        } else
            return new ResponseEntity<>(new Response("Failed to Book", HttpStatus.NO_CONTENT.value()), HttpStatus.NO_CONTENT);
    }


}

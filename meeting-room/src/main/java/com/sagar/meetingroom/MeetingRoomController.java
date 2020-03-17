package com.sagar.meetingroom;

import com.sagar.entity.MeetingRoom;
import com.sagar.entity.ReservationDate;
import com.sagar.interfaces.MeetingRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/meetingroom")
public class MeetingRoomController {

    @Autowired
    private MeetingRoomService meetingRoomServiceImp;

    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = "application/json")
    public List<MeetingRoom> getAllMeetingRoom() {
        List<MeetingRoom> rooms = meetingRoomServiceImp.getMeetingRoom();
        return rooms;
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<MeetingRoom> getUserById(@PathVariable("id") Integer id) {
        System.out.println("Fetching Meeting Room with id " + id);
        MeetingRoom meetingroom = meetingRoomServiceImp.findById(id);
        if (meetingroom == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(meetingroom, HttpStatus.OK);
    }

    @PostMapping(value = "/create", headers = "Accept=application/json")
    public ResponseEntity<String> createUser(@RequestBody MeetingRoom meetingroom, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Meeting Room " + meetingroom.getName());
        meetingRoomServiceImp.createMeetingRoom(meetingroom);
        return new ResponseEntity<>("Meeting Room Created Successfully", HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}", headers = "Accept=application/json")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Integer id) {
        MeetingRoom room = meetingRoomServiceImp.findById(id);
        if (room == null) {
            return new ResponseEntity<>("No such meeting room", HttpStatus.NOT_FOUND);
        }
        meetingRoomServiceImp.deleteMeetingRoomById(id);
        return new ResponseEntity<>("Meeting Room Deleted", HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/update", headers = "Accept=application/json")
    public ResponseEntity<MeetingRoom> updateUserPartially(@RequestBody MeetingRoom room) {
        MeetingRoom meetingroom = meetingRoomServiceImp.findById(room.getId());
        if (meetingroom == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        MeetingRoom r1 = meetingRoomServiceImp.update(room, room.getId());
        return new ResponseEntity<>(r1, HttpStatus.OK);
    }

    @RequestMapping(value = "/date", method = RequestMethod.POST, produces = "application/json")
    public List<MeetingRoom> getAllReservationsByDate(@RequestBody ReservationDate dates) {
        System.out.println("Dates to enquire from: " + dates.getDf() + " to: " + dates.getDt());
        List<MeetingRoom> rooms = meetingRoomServiceImp.findMeetingRoomAvailable(dates.getDf(), dates.getDt());
        return rooms;
    }
}

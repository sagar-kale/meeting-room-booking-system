package com.sagar.interfaces;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sagar.entity.MeetingRoom;

@Service
@Transactional
public interface MeetingRoomService {
	
	public void createMeetingRoom(MeetingRoom meetingroom);
    public List<MeetingRoom> getMeetingRoom();
    public MeetingRoom findById(Integer id);
    public MeetingRoom update(MeetingRoom meetingroom, Integer id);
    public Boolean deleteMeetingRoomById(Integer id);
    public List<MeetingRoom> findMeetingRoomAvailable(Date db, Date de);
}

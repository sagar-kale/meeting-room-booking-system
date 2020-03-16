package com.sagar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="meeting_room")
public class MeetingRoom extends AuditEntity{
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="capacity")
	private int capacity;
	
	@Column(name="location")
	private String location;

	public MeetingRoom() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MeetingRoom(int id, String name, int capacity, String location) {
		super();
		this.id = id;
		this.name = name;
		this.capacity = capacity;
		this.location = location;
	}

	public MeetingRoom( String name, int capacity, String location) {
		super();
		this.name = name;
		this.capacity = capacity;
		this.location = location;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	

}

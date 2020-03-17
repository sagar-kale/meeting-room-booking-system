package com.sagar.entity;

import java.util.Date;

public class Test {
	
	private Integer id;
	private String test;
	private Date d;

	public Test(Integer id, String test, Date d) {
		super();
		this.id = id;
		this.test = test;
		this.d = d;
	}

	public Test() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public Date getD() {
		return d;
	}

	public void setD(Date d) {
		this.d = d;
	}

	@Override
	public String toString() {
		return "Test [id=" + id + ", test=" + test + ", d=" + d + "]";
	}
	
	
	

}

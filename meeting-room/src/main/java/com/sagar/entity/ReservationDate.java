package com.sagar.entity;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class ReservationDate {

	private Date df;
	private Date dt;

	public ReservationDate() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReservationDate(Date df, Date dt) {
		super();
		this.df = df;
		this.dt = dt;
	}

	public Date getDf() {
		return df;
	}

	public void setDf(Date df) {
		this.df = df;
	}

	public Date getDt() {
		return dt;
	}

	public void setDt(Date dt) {
		this.dt = dt;
	}


}

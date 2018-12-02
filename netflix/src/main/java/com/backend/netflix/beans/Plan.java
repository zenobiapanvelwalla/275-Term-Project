package com.backend.netflix.beans;

import java.util.Date;

public class Plan {
	
	Date strtDate;
	Date  endDate;
	int moneyPaid;
	String movieid;
	
	public String getMovieid() {
		return movieid;
	}


	public void setMovieid(String movieid) {
		this.movieid = movieid;
	}
	
	
	
	public Plan(Date strtDate, Date endDate, int moneyPaid,String movieid) {
		super();
		this.strtDate = strtDate;
		this.endDate = endDate;
		this.moneyPaid = moneyPaid;
		this.movieid=movieid;
	}
	
	
	public Date getStrtDate() {
		return strtDate;
	}
	public void setStrtDate(Date strtDate) {
		this.strtDate = strtDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public int getMoneyPaid() {
		return moneyPaid;
	}
	public void setMoneyPaid(int moneyPaid) {
		this.moneyPaid = moneyPaid;
	}
	
	

}

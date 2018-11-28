package com.backend.netflix.beans;

import java.util.Date;

public class Plan {
	
	Date strtDate;
	Date  endDate;
	int moneyPaid;
	
	
	public Plan(Date strtDate, Date endDate, int moneyPaid) {
		super();
		this.strtDate = strtDate;
		this.endDate = endDate;
		this.moneyPaid = moneyPaid;
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

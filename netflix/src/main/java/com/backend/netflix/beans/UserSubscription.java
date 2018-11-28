package com.backend.netflix.beans;

import java.util.Date;

public class UserSubscription {
	
	
	private int Uid;
	private int Months;
	
private Date startDate;

private Date endDate;
	
	public int getUid() {
	return Uid;
}

public void setUid(int uid) {
	Uid = uid;
}

public UserSubscription(int uid, int months, Date startDate, Date endDate) {
	super();
	Uid = uid;
	Months = months;
	this.startDate = startDate;
	this.endDate = endDate;
}

public int getMonths() {
	return Months;
}

public void setMonths(int months) {
	Months = months;
}

public Date getStartDate() {
	return startDate;
}

public void setStartDate(Date startDate) {
	this.startDate = startDate;
}

public Date getEndDate() {
	return endDate;
}

public void setEndDate(Date endDate) {
	this.endDate = endDate;
}

	

}

package com.backend.netflix.vo;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_subscrition")
public class UserSubScription {
	
	private int Uid;
	private int Months;
	

	private Date startDate;
	
	private Date endDate;

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int subscritionId;
	

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

	
	
	
	
	public int getSubscritionId() {
		return subscritionId;
	}
	public void setSubscritionId(int subscritionId) {
		this.subscritionId = subscritionId;
	}
	
	public int getUid() {
		return Uid;
	}
	public void setUid(int uid) {
		Uid = uid;
	}
	public int getMonths() {
		return Months;
	}
	public void setMonths(int months) {
		Months = months;
	}
	
	

}

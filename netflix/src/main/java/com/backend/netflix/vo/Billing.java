package com.backend.netflix.vo;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.lang.Nullable;

@Entity
@Table(name="billing")
public class Billing {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int billId;
	public int userId;
	private int moneyPaid;
	
	private Date billdate;
	public Date getBilldate() {
		return billdate;
	}

	public void setBilldate(Date billdate) {
		this.billdate = billdate;
	}

	
	@Enumerated(EnumType.STRING)
	private PaidStatus pstatus;
	
	@Nullable
	private String movieId;
	

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	
	
	
	
	public int getBillId() {
		return billId;
	}

	public void setBillId(int billId) {
		this.billId = billId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getMoneyPaid() {
		return moneyPaid;
	}

	public void setMoneyPaid(int moneyPaid) {
		this.moneyPaid = moneyPaid;
	}

	public PaidStatus getPstatus() {
		return pstatus;
	}

	public void setPstatus(PaidStatus pstatus) {
		this.pstatus = pstatus;
	}

}

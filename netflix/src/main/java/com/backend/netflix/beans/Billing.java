package com.backend.netflix.beans;

import java.sql.Date;

import com.backend.netflix.vo.PaidStatus;

public class Billing {

	
	private PaidStatus pstatus;
	private Date billdate;
public Date getBilldate() {
		return billdate;
	}

	public void setBilldate(Date billdate) {
		this.billdate = billdate;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

private int billId;
	
	public int userid;
	
	
public Billing(int billId, int userid) {
		super();
		this.billId = billId;
		this.userid = userid;
	}

public PaidStatus getPstatus() {
		return pstatus;
	}

	public void setPstatus(PaidStatus pstatus) {
		this.pstatus = pstatus;
	}

	public int getBillId() {
		return billId;
	}

	public void setBillId(int billId) {
		this.billId = billId;
	}

	public Billing(PaidStatus pstatus, int billId, int userid) {
		super();
		this.pstatus = pstatus;
		this.billId = billId;
		this.userid = userid;
	}

	public Billing(PaidStatus pstatus, Date billdate, int billId, int userid) {
		super();
		this.pstatus = pstatus;
		this.billdate = billdate;
		this.billId = billId;
		this.userid = userid;
	}

	public int getUserId() {
		return userid;
	}

	public void setUserId(int userId) {
		this.userid = userId;
	}


	
	
}

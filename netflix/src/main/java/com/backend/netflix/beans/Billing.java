package com.backend.netflix.beans;

import com.backend.netflix.vo.PaymentType;

import java.sql.Date;

public class Billing {

	
	private PaymentType paymentType;
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

public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public int getBillId() {
		return billId;
	}

	public void setBillId(int billId) {
		this.billId = billId;
	}

	public Billing(PaymentType paymentType, int billId, int userid) {
		super();
		this.paymentType = paymentType;
		this.billId = billId;
		this.userid = userid;
	}

	public Billing(PaymentType paymentType, Date billdate, int billId, int userid) {
		super();
		this.paymentType = paymentType;
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

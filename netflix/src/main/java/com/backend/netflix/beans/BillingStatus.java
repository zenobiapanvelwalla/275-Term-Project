package com.backend.netflix.beans;

import com.backend.netflix.vo.PaymentType;

public class BillingStatus {

	
	private PaymentType pstatus;
	
private int billId;
	
	public int userid;
	
	
public BillingStatus(int billId, int userid) {
		super();
		this.billId = billId;
		this.userid = userid;
	}

public PaymentType getPstatus() {
		return pstatus;
	}

	public void setPstatus(PaymentType pstatus) {
		this.pstatus = pstatus;
	}

	public int getBillId() {
		return billId;
	}

	public void setBillId(int billId) {
		this.billId = billId;
	}

	public BillingStatus(PaymentType pstatus, int billId, int userid) {
		super();
		this.pstatus = pstatus;
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

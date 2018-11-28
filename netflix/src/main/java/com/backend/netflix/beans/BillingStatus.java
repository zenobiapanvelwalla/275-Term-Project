package com.backend.netflix.beans;

import com.backend.netflix.vo.PaidStatus;

public class BillingStatus {

	
	private PaidStatus pstatus;
	
	
private int billId;
	
	public int userid;
	
	
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

	public BillingStatus(PaidStatus pstatus, int billId, int userid) {
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

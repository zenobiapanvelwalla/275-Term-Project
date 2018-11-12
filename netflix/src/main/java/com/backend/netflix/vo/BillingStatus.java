package com.backend.netflix.vo;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="billing_Status")
public class BillingStatus {
	
	@Enumerated(EnumType.STRING)
	private PaidStatus pstatus;


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int billId;
	
	public int userId;
	
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

	public PaidStatus getPstatus() {
		return pstatus;
	}

	public void setPstatus(PaidStatus pstatus) {
		this.pstatus = pstatus;
	}

	
	
	
	
	

}

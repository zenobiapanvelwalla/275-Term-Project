package com.backend.netflix.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	
	@Nullable
	private String movieId;
	

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	@Enumerated(EnumType.STRING)

	private PaymentType pType;
	
	
	
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

	public PaymentType getPaymentType() {
		return pType;
	}

	public void setPaymentType(PaymentType pType) {
		this.pType = pType;
	}

}

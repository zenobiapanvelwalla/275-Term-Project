package com.backend.netflix.beans;

import java.math.BigDecimal;
import java.util.List;

public class MonthlyDetails {
	
	private List<Integer> userId;
    private List<Integer> month;
    
	public List<Integer> getUserId() {
		return userId;
	}
	public void setUserId(List<Integer> userId) {
		this.userId = userId;
	}
	public List<Integer> getMonth() {
		return month;
	}
	public void setMonth(List<Integer> month) {
		this.month = month;
	}
	
	
}

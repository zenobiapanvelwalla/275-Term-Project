package com.backend.netflix.beans;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public class MonthlyDetails {
	
	private List<BigInteger> userCount;
    private List<String> months;
    
	public List<BigInteger> getUserCount() {
		return userCount;
	}
	public void setUserCount(List<BigInteger> userCount) {
		this.userCount = userCount;
	}
	public List<String> getMonth() {
		return months;
	}
	public void setMonth(List<String> months) {
		this.months = months;
	}
	
	
}

package com.backend.netflix.beans;

import java.math.BigInteger;
import java.util.List;

public class userRegistered {
	
	private List<BigInteger> userId;
    public List<BigInteger> getUserId() {
		return userId;
	}

	public void setUserId(List<BigInteger> userId) {
		this.userId = userId;
	}
	public List<Integer> getMonth() {
		return month;
	}
	public void setMonth(List<Integer> month) {
		this.month = month;
	}
	private List<Integer> month;

}

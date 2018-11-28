package com.backend.netflix.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.backend.netflix.beans.BillingStatus;
import com.backend.netflix.repository.BillingStatusRepository;


public class BillingService {
	
	@Autowired
	private BillingStatusRepository billingStatusRepository;

//	public String getStat(int uid) {
//		// TODO Auto-generated method stub
//		
//		
//		return billingStatus.getPstatus().toString();
//	}

}

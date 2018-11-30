package com.backend.netflix.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.netflix.vo.BillingStatus;
import com.backend.netflix.repository.BillingStatusRepository;

@Service
public class BillingService {
	
	@Autowired
	private BillingStatusRepository billingStatusRepository;

//	public String getStat(int uid) {
//		// TODO Auto-generated method stub
//		
//		
//		return billingStatus.getPstatus().toString();
//	}
	public BillingStatus findByUserId(int userId) {
		return billingStatusRepository.findByUserId(userId);
	}
}

package com.backend.netflix.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.netflix.vo.Billing;
import com.backend.netflix.repository.BillingRepository;
import org.springframework.stereotype.Service;

@Service
public class BillingService {

	@Autowired
	private BillingRepository billingRepository;

	//	public String getStat(int uid) {
//		// TODO Auto-generated method stub
//		
//		
//		return billingStatus.getPstatus().toString();
//	}
	public Billing findByUserId(int userId) {
		return billingRepository.findByUserId(userId);
	}


//	public int getCountOfUniquePayPerViewUsers() {
//		return billingRepository.getCountOfUniquePayPerViewUsers();
//	}
}
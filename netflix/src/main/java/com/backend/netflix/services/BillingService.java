package com.backend.netflix.services;

import com.backend.netflix.vo.PaymentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.netflix.vo.Billing;
import com.backend.netflix.repository.BillingRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BillingService {

	@Autowired
	private BillingRepository billingRepository;



	public void addBillingDetails(int userId, int movieId, int moneyPaid, String paymentType){

		Billing billing= new Billing();
		billing.setUserId(userId);
		if(paymentType.compareTo("PayPerViewOnly")==0) {
			billing.setPaymentType(PaymentType.PayPerViewOnly);
		} else if (paymentType.compareTo("Paid")==0) {
			billing.setPaymentType(PaymentType.Paid);
		}
		billing.setMovieId(movieId);
		billing.setMoneyPaid(moneyPaid);
		billing.setBilldate(LocalDateTime.now());

		billingRepository.save(billing);

	}

	public List<Integer> getListOfMoviesUserHasPaidFor(int userId) {
		return billingRepository.getListOfMoviesUserHasPaidFor(userId);
	}



	public Billing findByUserId(int userId) {
		return billingRepository.findByUserId(userId);
	}


//	public int getCountOfUniquePayPerViewUsers() {
//		return billingRepository.getCountOfUniquePayPerViewUsers();
//	}
}
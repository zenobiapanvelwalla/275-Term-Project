package com.backend.netflix.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.netflix.vo.Billing;
import com.backend.netflix.vo.UserSubscription;
import com.backend.netflix.repository.BillingStatusRepository;
import com.backend.netflix.repository.UserRepository;
import com.backend.netflix.repository.UserSubscriptionRepository;
import com.backend.netflix.vo.PaymentType;
import com.backend.netflix.vo.User;

@Service
public class UserSubscriptionService {

	@Autowired
	private BillingStatusRepository billingStatusRepository;
	
	@Autowired
	private UserSubscriptionRepository userSubscriptionRepository;
	
	@Autowired
	private UserRepository userRepo;
	
//	for checking the subscribed user or not
	public UserSubscription subscribedDetails(int uid) {
	
		UserSubscription userSubscription=null;
		UserSubscription userSubsription=userSubscriptionRepository.findByUserId(uid);
//		boolean subscriptionPresent = false;
//		if(userSubsription!=null) {
			
			return userSubscription;
//		}	
	}
	


	public String subscribeUser(int userId, Date startDate, Date endDate, int moneyPaid) {

		
		User user = userRepo.findById(userId).get();
		
		java.sql.Date sDate = new java.sql.Date(startDate.getTime());
	
		java.sql.Date eDate = new java.sql.Date(endDate.getTime());
	
		UserSubscription newSubscription= subscribedDetails(userId);
		if(newSubscription!=null) {
			java.sql.Date endDate_existing=newSubscription.getEndDate();
			Calendar c = Calendar.getInstance();
//			c.add(arg0, arg1);
////			double millsecondsperDay=;
//			java.sql.Date newEndDate=(( eDate.getTime() - sDate.getTime() )) ;
////					+ endDate_existing;
		newSubscription.setEndDate(endDate_existing);
			
		}else {
			newSubscription=new UserSubscription();
			newSubscription.setEndDate(eDate);
		}
		
		newSubscription.setStartDate(sDate);
		newSubscription.setUserId(userId);
		newSubscription.setMonths(moneyPaid/10);

		Billing billing= new Billing();
		billing.setUserId(userId);
		billing.setPaymentType(PaymentType.subscription);
		billing.setMoneyPaid(moneyPaid);
	
		billingStatusRepository.save(billing);
		
		userSubscriptionRepository.save(newSubscription);
		return "Subscribed thanks";
	}
	
	public UserSubscription findByUserId(int userId) {
		return userSubscriptionRepository.findByUserId(userId);
	}



	public String subscribeUserPerMovie(int uid, Date startDate, Date endDate, int moneyPaid, String movieid) {
		// TODO Auto-generated method stub
		UserSubscription newSubscription= new UserSubscription();
		User user = userRepo.findById(uid).get();
		//newSubscription.setUser(user);
		newSubscription.setUserId(uid);
		newSubscription.setMonths(1);
		java.sql.Date sDate = new java.sql.Date(startDate.getTime());
		newSubscription.setStartDate(sDate);
		java.sql.Date eDate = new java.sql.Date(endDate.getTime());
		newSubscription.setEndDate(eDate);
		//BillingStatus billingStatus= new BillingStatus(PaidStatus.paid,0,uid);
		Billing billing= new Billing();
		billing.setUserId(uid);
		billing.setPaymentType(PaymentType.subscription);
		billing.setMoneyPaid(moneyPaid);
		billing.setMovieId(movieid);
		
		billingStatusRepository.save(billing);
		
		userSubscriptionRepository.save(newSubscription);
		return "Subscribed thanks";
		
		
	}
	
	public int getCountOfUniqueSubscriptionUsers(){
		return userSubscriptionRepository.getCountOfUniqueSubscriptionUsers();
	}


}

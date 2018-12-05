package com.backend.netflix.services;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.netflix.vo.Billing;
import com.backend.netflix.vo.UserSubscription;
import com.backend.netflix.repository.BillingRepository;

import com.backend.netflix.repository.UserRepository;
import com.backend.netflix.repository.UserSubscriptionRepository;
import com.backend.netflix.vo.PaymentType;

@Service
public class UserSubscriptionService {

	@Autowired
	private BillingRepository billingRepository;
	
	@Autowired
	private UserSubscriptionRepository userSubscriptionRepository;
	
	@Autowired
	private UserRepository userRepo;

	public List<UserSubscription> findLatestSubscriptionByUserId(int userId) {
		return userSubscriptionRepository.findLatestSubscriptionByUserId(userId);
	}

	public UserSubscription addSubscription(int userId, int months) {
		List<UserSubscription> subscriptionList = userSubscriptionRepository.findLatestSubscriptionByUserId(userId);
		UserSubscription subscription = null;
		if(subscriptionList.size()>0) {

			//UserSubscription subscription = null;
			subscription = subscriptionList.get(0);
			LocalDateTime endDate = subscription.getEndDate();

			LocalDateTime now = LocalDateTime.now();

			if (endDate.compareTo(now) > 0) {
				//user already has a SubscriptionOnly--> update end date based on months
				LocalDateTime futureDate = endDate.plusMonths(months);

				subscription.setEndDate(futureDate.with(LocalTime.MIN));
				System.out.println("----------------Futeure DATE:" +futureDate.with(LocalTime.MIN));
				subscription.setMonths(months);

				userSubscriptionRepository.save(subscription);

				addBillingDetails(userId,months,now,PaymentType.SubscriptionOnly);

				return subscription;

			} else {
				return utilAddNewSubscription(userId, months);
			}
		}
		else
			return utilAddNewSubscription(userId,months);

		//return subscription;
	}

	public UserSubscription utilAddNewSubscription(int userId,int months){
		UserSubscription newSubscription = new UserSubscription();
		newSubscription.setUserId(userId);
		newSubscription.setMonths(months);

		LocalDateTime startDate = LocalDateTime.now();
		LocalDateTime enddate = startDate.plusMonths(months);

		newSubscription.setStartDate(startDate);
		newSubscription.setEndDate(enddate.with(LocalTime.MIN));

		addBillingDetails(userId,months,startDate,PaymentType.SubscriptionOnly);
		userSubscriptionRepository.save(newSubscription);
		return newSubscription;
	}

	public void addBillingDetails(int userId,int months,LocalDateTime billDate,PaymentType paymentType) {

		Billing billing= new Billing();
		billing.setUserId(userId);
		billing.setPaymentType(paymentType);
		billing.setMoneyPaid(months*10);
		billing.setBilldate(billDate);

		billingRepository.save(billing);

	}

	public Boolean checkIfSubscriptionIsActive(int userId) {
		List<UserSubscription> subscriptionList = userSubscriptionRepository.findLatestSubscriptionByUserId(userId);
		if( subscriptionList.size()>0) {
			UserSubscription subscription = subscriptionList.get(0);
			LocalDateTime endDate = subscription.getEndDate();
			LocalDateTime now = LocalDateTime.now();

			if (endDate.compareTo(now) > 0) {
				return true;
			}
			return false;
		}
		return false;
	}
	/*
	public UserSubscription findByUserId(int userId) {
		return userSubscriptionRepository.findByUserId(userId);
	}

	public int getCountOfUniqueSubscriptionUsers(){
		return userSubscriptionRepository.getCountOfUniqueSubscriptionUsers();
	}

	*/
}

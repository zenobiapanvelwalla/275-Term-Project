package com.backend.netflix.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.backend.netflix.vo.PaymentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.netflix.vo.Billing;
import com.backend.netflix.vo.User;
import com.backend.netflix.repository.UserActivityRepository;
import com.backend.netflix.repository.BillingRepository;
import com.backend.netflix.repository.UserRepository;

import org.springframework.stereotype.Service;

@Service
public class ReportService {


	@Autowired
	private UserActivityRepository UserActivityRepository;

	@Autowired
	private BillingRepository billingRepository;

	@Autowired
	private UserRepository userRepository;

	public Billing findByUserId(int userId) {
		return billingRepository.findByUserId(userId);
	}

	public List<User> getAllUniqueSubscriptionUsers( PaymentType paymentType) {

		List<User> allSubscribedUsers = new ArrayList<>();
		List<Integer> UniqueBillUserids = billingRepository.getUserIdListByPaymentType(paymentType);
		Set<User> allSubscribedUserUnique= new HashSet<User>();
		for(int userIds:UniqueBillUserids) {
			allSubscribedUserUnique.add((User) userRepository.findById(userIds));
		}
		allSubscribedUsers.addAll(allSubscribedUserUnique);
		return allSubscribedUsers ;
	}

	public List<User> getAllUniqueActiveusers() {
		List<User> UniqueActiveusers=new ArrayList<>();
		List<Integer> UniqueactiveUserids=UserActivityRepository.getUniqueActiveusers();
		Set<User> allSubsribedUserUnique= new HashSet<User>();
		for(int userIds:UniqueactiveUserids) {
			allSubsribedUserUnique.add((User) userRepository.findById(userIds));
		}
		UniqueActiveusers.addAll(allSubsribedUserUnique);
		return UniqueActiveusers ;
		// TODO Auto-generated method stub

	}



	public int getIncomeBasedonSubscription(PaymentType paymentType) {
		//		https://stackoverflow.com/questions/7182996/java-get-month-integer-from-date  refer this
		//creating instances of java.util.Date which represents today's date and time
		java.util.Date now = new java.util.Date();
		now.getMonth();
		// TODO Auto-generated method stub
		return 0;
	}



	public int getTotalIncome() {
		//		https://stackoverflow.com/questions/7182996/java-get-month-integer-from-date  refer this
		//creating instances of java.util.Date which represents today's date and time

		int genralSubscriptionIncome=getIncomeBasedonSubscription(PaymentType.general);
		int perMovieSubscriptionIncome=getIncomeBasedonSubscription(PaymentType.perMovie);

		// TODO Auto-generated method stub
		return genralSubscriptionIncome+perMovieSubscriptionIncome;
	}



	public HashMap<Integer, List<User>> getMonthbyMonthRegisteresUsers() {
		// TODO Auto-generated method stub
		return null;
	}









}

package com.backend.netflix.services;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

import com.backend.netflix.beans.MonthlyDetails;
import com.backend.netflix.beans.MonthlyIncome;
import com.backend.netflix.beans.userRegistered;
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
	private UserRepository UserRepository;

	@Autowired
	private BillingRepository billingRepository;

	@Autowired
	private UserRepository userRepository;

	public Billing findByUserId(int userId) {
		return billingRepository.findByUserId(userId);
	}


	public MonthlyIncome getIncomeByMonth(String paymentType) {
		List<Integer> monthList= null;
		List<BigDecimal> incomeList= null ;

		//if(paymentType.compareTo("PayPerViewOnly")==0) {

		incomeList = billingRepository.getIncomeListBasedOnPaymentType(paymentType);
		monthList = billingRepository.getMonthsListBasedOnPaymentType(paymentType);

		List<String> monthListStr = convertIntToMonths(monthList);

		MonthlyIncome monthlyIncome = new MonthlyIncome();
		monthlyIncome.setIncome(incomeList);
		monthlyIncome.setMonth(monthListStr);

		return monthlyIncome;
	}

	List<String> convertIntToMonths(List<Integer> monthList) {
		List<String> months = new ArrayList<String>();
		String[] keys = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sept","Oct","Nov","Dec"};
		for(int i=0;i<monthList.size();i++){
			months.add(keys[monthList.get(i)-1]);
		}
		return months;
	}

	public MonthlyIncome getIncomeTotal() {

		List<Integer> monthList= null;
		List<BigDecimal> incomeList= null ;

		//if(paymentType.compareTo("PayPerViewOnly")==0) {

		incomeList = billingRepository.getIncomeListForTotalIncome();
		monthList = billingRepository.getMonthsListForTotalIncome();

		List<String> monthListStr = convertIntToMonths(monthList);

		MonthlyIncome monthlyIncome = new MonthlyIncome();
		monthlyIncome.setIncome(incomeList);
		monthlyIncome.setMonth(monthListStr);

		return monthlyIncome;

	}

//	public BigInteger getCountOfUniqueSubscriptionUsers() {
//
//		return billingRepository.getCountOfUniqueSubscriptionUsers();
//	}

	
	

	
	public MonthlyDetails getUniqueActiveUserWatchedOnemovieperMonth() {
		
		List<BigInteger> userList= null;
		List<Integer> monthList= null ;
		userList =UserActivityRepository.getUsersWatchedMovedPerMonth();
		monthList = UserActivityRepository.getMonthsWatchedMovedPerMonth();
		List<String> monthListStr = convertIntToMonths(monthList);

		MonthlyDetails MonthlyDetails = new MonthlyDetails();
		MonthlyDetails.setMonth(monthListStr);
		MonthlyDetails.setUserCount(userList);
		return MonthlyDetails;

	}


	public userRegistered getMonthlyRegisteredUsers() {
		List<BigInteger> userList= null;
		List<Integer> monthList= null ;
		monthList =UserRepository.getMonthuserRegisteredMonthly();
		userList = UserRepository.getUserRegisteredMonthly();
		List<String> monthListStr = convertIntToMonths(monthList);

		userRegistered userRegistered = new userRegistered();
		userRegistered.setMonth(monthList);
		userRegistered.setUserId(userList);
		return userRegistered;

	}


	public MonthlyDetails getCountOfUniquePayPerViewUsers() {
		List<BigInteger> userCountList= null;
		List<Integer> monthList= null ;
		monthList =billingRepository.getCountOfUniquePayPerViewUsers_Months();
		userCountList = billingRepository.getCountOfUniquePayPerViewUsers_UserCount();
		List<String> monthListStr = convertIntToMonths(monthList);
		
		MonthlyDetails MonthlyDetails = new MonthlyDetails();
		MonthlyDetails.setMonth(monthListStr);
		MonthlyDetails.setUserCount(userCountList);
		return MonthlyDetails;
	}

}

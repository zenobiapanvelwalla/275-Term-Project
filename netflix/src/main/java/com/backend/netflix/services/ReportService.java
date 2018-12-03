package com.backend.netflix.services;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

import com.backend.netflix.beans.MonthlyIncome;
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


	public MonthlyIncome getIncomeByMonth(String paymentType) {
		List<Integer> monthList= null;
		List<BigDecimal> incomeList= null ;

		//if(paymentType.compareTo("payPerView")==0) {

		incomeList = billingRepository.getIncomeListBasedOnPaymentType(paymentType);
		monthList = billingRepository.getMonthsListBasedOnPaymentType(paymentType);

		List<String> monthListStr = convertIntToMonths(monthList);

		MonthlyIncome monthlyIncome = new MonthlyIncome();
		monthlyIncome.setIncome(incomeList);
		monthlyIncome.setMonth(monthListStr);
		/*for(int i=0; i<monthList.size();i++) {

			MonthlyIncome monthlyIncome1 = new MonthlyIncome();
			monthlyIncome1.setIncome(incomeList.get(i));
			monthlyIncome1.setMonth(monthList.get(i));
			monthlyIncomes.add(monthlyIncome1);
		}*/

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

		//if(paymentType.compareTo("payPerView")==0) {

		incomeList = billingRepository.getIncomeListForTotalIncome();
		monthList = billingRepository.getMonthsListForTotalIncome();


//		for(int i=0; i<monthList.size();i++) {
//
//			MonthlyIncome monthlyIncome1 = new MonthlyIncome();
//			monthlyIncome1.setIncome(incomeList.get(i));
//			monthlyIncome1.setMonth(monthList.get(i));
//			monthlyIncomes.add(monthlyIncome1);
//		}
		List<String> monthListStr = convertIntToMonths(monthList);

		MonthlyIncome monthlyIncome = new MonthlyIncome();
		monthlyIncome.setIncome(incomeList);
		monthlyIncome.setMonth(monthListStr);

		return monthlyIncome;

	}

	public BigInteger getCountOfUniqueSubscriptionUsers() {

		return billingRepository.getCountOfUniqueSubscriptionUsers();
	}
/*
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

		int SubscriptionIncome=getIncomeBasedonSubscription(PaymentType.subscription);
		int payPerViewMovieIncome=getIncomeBasedonSubscription(PaymentType.payPerView);
		int paidMovieIncome = getIncomeBasedonSubscription(PaymentType.paid);

		// TODO Auto-generated method stub
		return SubscriptionIncome+payPerViewMovieIncome + paidMovieIncome;
	}



	public HashMap<Integer, List<User>> getMonthbyMonthRegisteresUsers() {

		return null;
	}

*/







}

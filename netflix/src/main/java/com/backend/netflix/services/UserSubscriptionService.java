package com.backend.netflix.services;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.netflix.vo.Billing;
import com.backend.netflix.vo.UserSubscription;
import com.backend.netflix.repository.BillingRepository;

import com.backend.netflix.repository.UserRepository;
import com.backend.netflix.repository.UserSubscriptionRepository;
import com.backend.netflix.vo.PaymentType;
import com.backend.netflix.vo.User;

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

	public void addSubscription(int userId, int months) {
		List<UserSubscription> subscriptionList = userSubscriptionRepository.findLatestSubscriptionByUserId(userId);
		if(subscriptionList.size()>0) {

			UserSubscription subscription = null;
			subscription = subscriptionList.get(0);
			LocalDateTime endDate = subscription.getEndDate();
			//converting java.sql.Date to java.util.Date
			//java.util.Date utilEndDate = new java.util.Date(sqlEndDate.getTime());
			//creating instances of java.util.Date which represents today's date and time
			LocalDateTime now = LocalDateTime.now();
			if (endDate.compareTo(now) > 0) {
				//user already has a subscription--> update end date based on months
				//int futureDay = 30 * months;
				LocalDateTime futureDate = endDate.plusMonths(months);
				//Date futureDate = addMonth(utilEndDate, months);
				//subscription.setEndDate(new java.sql.Date(futureDate.getTime()));
				subscription.setEndDate(futureDate);
				subscription.setMonths(months);

				addBillingDetails(userId,months,now,PaymentType.general);
			} else {
				utilAddNewSubscription(userId, months);
			}
		}
		else
			utilAddNewSubscription(userId,months);
	}

	public void utilAddNewSubscription(int userId,int months){
		UserSubscription newSubscription = new UserSubscription();
		newSubscription.setUserId(userId);
		newSubscription.setMonths(months);
		//Date startDate = new java.util.Date();
		LocalDateTime startDate = LocalDateTime.now();
		//int futureDay = months;
		//Date endDate = addMonth(startDate, months);
//		java.sql.Date sDate = new java.sql.Date(startDate.getTime());
//		java.sql.Date eDate = new java.sql.Date(endDate.getTime());
//
//		newSubscription.setStartDate(sDate);
//		newSubscription.setEndDate(eDate);
		LocalDateTime enddate = startDate.plusMonths(months);

		newSubscription.setStartDate(startDate);
		newSubscription.setEndDate(enddate);

		addBillingDetails(userId,months,startDate,PaymentType.general);
		userSubscriptionRepository.save(newSubscription);
	}

	public void addBillingDetails(int userId,int months,LocalDateTime billDate,PaymentType paymentType) {

		Billing billing= new Billing();
		billing.setUserId(userId);

		billing.setPaymentType(paymentType);

		billing.setMoneyPaid(months*10);
		//billing.setBilldate(new java.sql.Date(billDate.getTime()));
		billing.setBilldate(billDate);

		billingRepository.save(billing);

	}

	/*public static Date addMonth(Loca date, int months) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, months);
//		c.set(Calendar.HOUR_OF_DAY, 0);
//		c.set(Calendar.MINUTE, 0);
//		c.set(Calendar.SECOND, 0);
//		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}
*/

//	for checking the subscribed user or not
	public UserSubscription subscribedDetails(int uid) {
	
		UserSubscription userSubscription=null;
		UserSubscription userSubsription=userSubscriptionRepository.findByUserId(uid);
//		boolean subscriptionPresent = false;
//		if(userSubsription!=null) {
			
			return userSubscription;
//		}	
	}
	
/*

	public String subscribeUser(int userId, Date startDate, Date endDate, int moneyPaid) {

		User user = userRepo.findById(userId);

		java.sql.Date sDate = new java.sql.Date(startDate.getTime());
	
		java.sql.Date eDate = new java.sql.Date(endDate.getTime());
	
		UserSubscription newSubscription= subscribedDetails(userId);
		if(newSubscription!=null) {
			java.sql.Date endDate_existing=newSubscription.getEndDate();

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

		billing.setPaymentType(PaymentType.general);

		billing.setMoneyPaid(moneyPaid);
	
		billingRepository.save(billing);
		
		userSubscriptionRepository.save(newSubscription);
		return "Subscribed thanks";
	}

	public UserSubscription findByUserId(int userId) {
		return userSubscriptionRepository.findByUserId(userId);
	}



	public String subscribeUserPerMovie(int uid, Date startDate, Date endDate, int moneyPaid, String movieid) {

		UserSubscription newSubscription= new UserSubscription();
		User user = userRepo.findById(uid);
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
		billing.setPaymentType(PaymentType.perMovie);
		billing.setMoneyPaid(moneyPaid);
		billing.setMovieId(movieid);
		
		billingRepository.save(billing);
		
		userSubscriptionRepository.save(newSubscription);
		return "Subscribed thanks";
		
		
	}

	public int getCountOfUniqueSubscriptionUsers(){
		return userSubscriptionRepository.getCountOfUniqueSubscriptionUsers();
	}

	*/
}

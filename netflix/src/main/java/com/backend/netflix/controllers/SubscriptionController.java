package com.backend.netflix.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import com.backend.netflix.services.BillingService;
import com.backend.netflix.vo.Encryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.backend.netflix.beans.Plan;
import com.backend.netflix.vo.UserSubscription;
import com.backend.netflix.services.MovieService;
import com.backend.netflix.services.UserSubscriptionService;
import com.backend.netflix.services.UserService;
import com.backend.netflix.vo.Movie;
import com.backend.netflix.vo.User;

import javax.servlet.http.HttpSession;

//@ComponentScan(basePackages = "com.backend.netflix.beans,com.backend.netflix.services,com.backend.netflix.vo" ) ;
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class SubscriptionController {

	@Autowired
	private MovieService movieService;

	@Autowired
	private UserSubscriptionService userSubscriptionService;

	@Autowired
	private UserService userService ;

	@Autowired
	private BillingService billingService;

	@RequestMapping(value = "/verifySubscription", method = RequestMethod.GET)
	public boolean verifySubscription(HttpSession session){
		int userId =(int)session.getAttribute("userId");
		return userSubscriptionService.checkIfSubscriptionIsActive(userId);
	}

	@RequestMapping(value = "/subscribe/{noOfMonths}", method = RequestMethod.GET)
	ResponseEntity<?> addSubscription(@PathVariable int noOfMonths, HttpSession session) throws Exception {
		// *******************For testing ONLY
		//session.setAttribute("userId",1);
		System.out.println("----------------USerId:"+ session.getAttribute("userId"));
		HashMap<String, Object> response = new HashMap<>();
		int userId =(int)session.getAttribute("userId");

		//check if user has SubscriptionOnly already
		userSubscriptionService.addSubscription(userId, noOfMonths);

		response.put("success", true);
		response.put("message", "Subscription Added Successfully");
		response.put("isSubscribed", true);
		response.put("statusCode", 200);

		return new ResponseEntity(response, HttpStatus.CREATED);
	}

	//    user comes and check his SubscriptionOnly " subscribe:method" , if not then bills the new plan by selecting the dates
	//    no months and then pushes it to the "subscribeForParticularPlan " 

	//    check the subscribed or not
	/*
	@RequestMapping("/subscribe")
	public String subscribe(HttpSession session) throws Exception { 

		uid =(int)session.getAttribute("userId");
		String role=(String)session.getAttribute("role");
		UserSubscription userSubsription=userSubscriptionService.subscribedDetails(uid);
		Date endDate=userSubsription.getEndDate();
		//converting java.sql.Date to java.util.Date 
		java.util.Date enddate = new java.util.Date(endDate.getTime());
		//creating instances of java.util.Date which represents today's date and time
		java.util.Date now = new java.util.Date();
		if(enddate.compareTo(now) >0) {
			System.out.println("Already subscribed");
		}else {
			//        	proceeding to the billing page

		}
		return "plan selection";
	}

	@PostMapping("/subscribe/plan")
	public String subscribeForParticularPlan(@RequestBody Plan plan ) throws Exception { 
		
		//creating instances of java.util.Date which represents today's date and time
		java.util.Date now = new java.util.Date();
		Date startDate = plan.getStrtDate()==null?now:plan.getStrtDate();
		Date endDate = plan.getEndDate();
		int moneyPaid = plan.getMoneyPaid();
		String movieid= plan.getMovieid();
//		for permovie handling
		if(moneyPaid<10) {
			return userSubscriptionService.subscribeUserPerMovie(uid,startDate,endDate,moneyPaid,movieid);
		}
		return userSubscriptionService.subscribeUser(uid,startDate,endDate,moneyPaid);
	}
	

	@GetMapping("/unique-SubscriptionOnly-users")
	public int getCountOfUniqueSubscriptionUsers(HttpSession session) {

		return userSubscriptionService.getCountOfUniqueSubscriptionUsers();
	}
	*/

//	@GetMapping("/unique-pay-per-view-users")
//	public int getCountOfUniquePayPerViewUsers(HttpSession session) {
//
//		return billingService.getCountOfUniquePayPerViewUsers();
//	}



}




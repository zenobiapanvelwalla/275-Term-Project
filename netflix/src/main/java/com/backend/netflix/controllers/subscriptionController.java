package com.backend.netflix.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

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
import com.backend.netflix.services.subscriptionService;
import com.backend.netflix.services.UserService;
import com.backend.netflix.vo.Movie;
import com.backend.netflix.vo.User;

import javax.servlet.http.HttpSession;

//@ComponentScan(basePackages = "com.backend.netflix.beans,com.backend.netflix.services,com.backend.netflix.vo" ) ;
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class subscriptionController {

	@Autowired
	private MovieService movieService;

	@Autowired
	private subscriptionService subscriptionService;


	@Autowired
	private UserService userService ;

	public static int uid=0;


	//    user comes and check his subscription " subscribe:method" , if not then bills the new plan by selecting the dates 
	//    no months and then pushes it to the "subscribeForParticularPlan " 

	//    check the subscribed or not
	@RequestMapping("/subscribe")
	public String subscribe(HttpSession session) throws Exception { 


		uid=(int)session.getAttribute("userId");
		String role=(String)session.getAttribute("role");
		
		
		
		UserSubscription userSubsription=subscriptionService.subscribedDetails(uid);
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
		Date startDate=plan.getStrtDate()==null?now:plan.getStrtDate();
		Date endDate=plan.getEndDate();
		int moneyPaid=plan.getMoneyPaid();
//		Keep or make a validation in frontend
		if(moneyPaid<10) {
			return "Please pay atleast 10 dollars";
		}

		return subscriptionService.subscribeUser(uid,startDate,endDate,moneyPaid);
	}



}




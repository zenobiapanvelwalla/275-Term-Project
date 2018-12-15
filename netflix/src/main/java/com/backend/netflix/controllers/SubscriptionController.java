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
@CrossOrigin(origins = "http://ec2-34-220-9-51.us-west-2.compute.amazonaws.com:3000",allowCredentials="true")
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

		System.out.println("----------------USerId:"+ session.getAttribute("userId"));
		HashMap<String, Object> response = new HashMap<>();
		int userId =(int)session.getAttribute("userId");

		//check if user has SubscriptionOnly already
		UserSubscription subscription= userSubscriptionService.addSubscription(userId, noOfMonths);

		response.put("success", true);
		response.put("message", "Subscription Added Successfully");
		response.put("subscription", subscription);
		response.put("isSubscribed", true);
		response.put("statusCode", 200);

		return new ResponseEntity(response, HttpStatus.CREATED);
	}


}




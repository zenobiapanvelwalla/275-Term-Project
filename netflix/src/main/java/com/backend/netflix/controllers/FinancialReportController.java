package com.backend.netflix.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.backend.netflix.beans.MonthlyDetails;
import com.backend.netflix.beans.MonthlyIncome;
import com.backend.netflix.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.backend.netflix.beans.Plan;
import com.backend.netflix.beans.userRegistered;
import com.backend.netflix.services.MovieService;
import com.backend.netflix.services.ReportService;
import com.backend.netflix.services.UserSubscriptionService;
import com.backend.netflix.services.UserService;

import javax.servlet.http.HttpSession;

//@ComponentScan(basePackages = "com.backend.netflix.beans,com.backend.netflix.services,com.backend.netflix.vo" ) ;
@RestController
@CrossOrigin(origins = "http://ec2-34-220-9-51.us-west-2.compute.amazonaws.com:3000",allowCredentials="true")

public class FinancialReportController {

	@Autowired
	private MovieService movieService;

	@Autowired
	private UserSubscriptionService userSubscriptionService;

	@Autowired
	private UserService userService ;

	@Autowired
	private ReportService reportservice ;

	//Monthly subscription income, monthly pay-per-view income, and monthly total income, for the last 12 calendar months, including the current month.
	@RequestMapping(value = "/monthlyincome",method = RequestMethod.GET)
	public ResponseEntity<?> getMonthlyIncome(){

		HashMap<String, Object> response = new HashMap<String, Object>();
		MonthlyIncome monthlyIncomeSubscription = reportservice.getIncomeByMonth("SubscriptionOnly");

		MonthlyIncome monthlyIncomePayPerView = reportservice.getIncomeByMonth("PayPerViewOnly");

		MonthlyIncome monthlyIncomeTotal = reportservice.getIncomeTotal();

		response.put("success", true);
		response.put("message", "success");
		response.put("subscriptionIncome", monthlyIncomeSubscription);
		response.put("payPerMovieIncome", monthlyIncomePayPerView);
		response.put("totalIncome", monthlyIncomeTotal);
		response.put("statusCode", 200);
		return new ResponseEntity(response, HttpStatus.OK);
	}

	// unique pay-per-view users (those who have played at least one Pay-Per-View movie
	@RequestMapping(value = "/count-unique-pay-per-view-users",method = RequestMethod.GET)
	public ResponseEntity<?> getCountOfUniquePayPerViewUsers(){

		HashMap<String, Object> response = new HashMap<String, Object>();
		MonthlyDetails MonthlyDetails = reportservice.getCountOfUniquePayPerViewUsers();

		response.put("success", true);
		response.put("message", MonthlyDetails);
		response.put("statusCode", 200);
		return new ResponseEntity(response, HttpStatus.OK);
	}

	//Numbers of unique subscription users
	@RequestMapping(value = "/count-unique-subscription-users",method = RequestMethod.GET)
	public ResponseEntity<?> getCountOfUniqueSubscriptonUsers(){

		HashMap<String, Object> response = new HashMap<String, Object>();
		MonthlyDetails MonthlyDetails = reportservice.getCountOfUniqueSubscriptionUsers();

		response.put("success", true);
		response.put("message", MonthlyDetails);
		response.put("statusCode", 200);
		return new ResponseEntity(response, HttpStatus.OK);
	}

	// total unique active users (those who played at least one movie in the month for last 12 months
	@RequestMapping(value = "/count-total-unique-active-users",method = RequestMethod.GET)
	public ResponseEntity<?> getUniqueActiveUserWatchedOnemovieperMonth(){

		HashMap<String, Object> response = new HashMap<String, Object>();
		MonthlyDetails MonthlyDetails = reportservice.getUniqueActiveUserWatchedOnemovieperMonth();

		response.put("success", true);
		response.put("message", MonthlyDetails);
		response.put("statusCode", 200);
		return new ResponseEntity(response, HttpStatus.OK);
	}

	//total unique users (all registered users), month by month for the last 12 calendar months
	@RequestMapping(value = "/count-unique-registered-users",method = RequestMethod.GET)
	public ResponseEntity<?> getMonthlyRegisteredUsers(){

		HashMap<String, Object> response = new HashMap<String, Object>();
		MonthlyDetails monthlyUserRegistered = reportservice.getMonthlyRegisteredUsers();

		response.put("success", true);
		response.put("message", monthlyUserRegistered);
		response.put("statusCode", 200);
		return new ResponseEntity(response, HttpStatus.OK);
	}



}


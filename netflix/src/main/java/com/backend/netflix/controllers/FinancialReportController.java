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
@CrossOrigin(origins = "http://localhost:3000",allowCredentials="true")
public class FinancialReportController {

	@Autowired
	private MovieService movieService;

	@Autowired
	private UserSubscriptionService userSubscriptionService;

	@Autowired
	private UserService userService ;

	@Autowired
	private ReportService reportservice ;

	@RequestMapping(value = "/monthlyincome",method = RequestMethod.GET)
	public ResponseEntity<?> getMonthlyIncome(){

		HashMap<String, Object> response = new HashMap<String, Object>();
		MonthlyIncome monthlyIncomeSubscription = reportservice.getIncomeByMonth("SubscriptionOnly");

		MonthlyIncome monthlyIncomePayPerView = reportservice.getIncomeByMonth("PayPerViewOnly");

		//List<MonthlyIncome> monthlyIncomePaid = reportservice.getIncomeByMonth("Paid");
		MonthlyIncome monthlyIncomeTotal = reportservice.getIncomeTotal();

		response.put("success", true);
		response.put("message", "success");
		response.put("subscriptionIncome", monthlyIncomeSubscription);
		response.put("payPerMovieIncome", monthlyIncomePayPerView);
		response.put("totalIncome", monthlyIncomeTotal);
		response.put("statusCode", 200);
		return new ResponseEntity(response, HttpStatus.OK);
	}

	
	
//	"http://localhost:8080/uniqueSubscriptionUsersMonthly/subscription"
//	"http://localhost:8080/uniqueSubscriptionUsersMonthly/payPerView"
	@RequestMapping(value = "/uniqueSubscriptionUsersMonthly/{ptype}",method = RequestMethod.GET)
	public ResponseEntity<?> getCountOfUniqueSubscriptonUsers(@PathVariable String  ptype){

		HashMap<String, Object> response = new HashMap<String, Object>();
		MonthlyDetails MonthlyDetails = reportservice.getUsersBasedOnSubsription(ptype);

		response.put("success", true);
		response.put("message", MonthlyDetails);
		response.put("statusCode", 200);
		return new ResponseEntity(response, HttpStatus.OK);
	}
	
	
	
	

//	"http://localhost:8080/uniqueSubscriptionUsersMonthly"
	@RequestMapping(value = "/UniqueActiveUserWatchedOnemovieperMonth",method = RequestMethod.GET)
	public ResponseEntity<?> getUniqueActiveUserWatchedOnemovieperMonth(){

		HashMap<String, Object> response = new HashMap<String, Object>();
		MonthlyDetails MonthlyDetails = reportservice.getUniqueActiveUserWatchedOnemovieperMonth();

		response.put("success", true);
		response.put("message", MonthlyDetails);
		response.put("statusCode", 200);
		return new ResponseEntity(response, HttpStatus.OK);
	}
	

//	"http://localhost:8080/MonthlyregisterUsers"
	@RequestMapping(value = "/MonthlyregisterUsers",method = RequestMethod.GET)
	public ResponseEntity<?> getMonthlyRegisteredUsers(){

		HashMap<String, Object> response = new HashMap<String, Object>();
		userRegistered userRegistered = reportservice.getMonthlyRegisteredUsers();

		response.put("success", true);
		response.put("message", userRegistered);
		response.put("statusCode", 200);
		return new ResponseEntity(response, HttpStatus.OK);
	}



}


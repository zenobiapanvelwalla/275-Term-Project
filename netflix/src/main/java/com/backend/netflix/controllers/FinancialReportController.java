package com.backend.netflix.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

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
import com.backend.netflix.services.MovieService;
import com.backend.netflix.services.ReportService;
import com.backend.netflix.services.UserSubscriptionService;
import com.backend.netflix.services.UserService;

import javax.servlet.http.HttpSession;

//@ComponentScan(basePackages = "com.backend.netflix.beans,com.backend.netflix.services,com.backend.netflix.vo" ) ;
@RestController
@CrossOrigin(origins = "http://localhost:3000")
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

	/*
	@RequestMapping(value = "/uniqueSubscriptionUsers",method = RequestMethod.GET)
	public ResponseEntity<?> getCountOfUniqueSubscriptonUsers(){

		HashMap<String, Object> response = new HashMap<String, Object>();
		int count = reportservice.getCountOfUniqueSubscriptionUsers();

		response.put("success", true);
		response.put("message", count);
		response.put("statusCode", 200);
		return new ResponseEntity(response, HttpStatus.OK);
	}
	*/



/*
	//	Numbers of unique SubscriptionOnly users/ pay per view users,
	@RequestMapping("/uniqueSubscriptionUsers")
	public  List<User> uniqueSubscriptionUsers(@PathVariable PaymentType paymentType){
		List<User> uniqueSubusers=reportservice.getAllUniqueSubscriptionUsers(paymentType);
		return uniqueSubusers;	
	}


	//	total unique users who played atleast a movie in month
	@RequestMapping("/totalUniqueActiveUsers")
	public  List<User> totalUniqueActiveUsers(){
		List<User> uniqueSubusers=reportservice.getAllUniqueActiveusers();
		return uniqueSubusers;	
	}


	//Not finished still


	//Not finished Still
	@RequestMapping("/TotalIncomePerMonth/")
	public int montlyTotalIncome(){
		return reportservice.getTotalIncome();
	}



	//users registered, month by month for the last 12 calendar months,
	@RequestMapping("/MonthbyMonthRegisteresUsers/")
	public HashMap<Integer,List<User>> MonthbyMonthRegisteresUsers(){
		return reportservice.getMonthbyMonthRegisteresUsers();
	}


*/
}


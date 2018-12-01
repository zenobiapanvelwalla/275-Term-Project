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
import com.backend.netflix.services.ReportService;
import com.backend.netflix.services.UserSubscriptionService;
import com.backend.netflix.services.UserService;
import com.backend.netflix.vo.Movie;
import com.backend.netflix.vo.PaidStatus;
import com.backend.netflix.vo.User;

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

	//	Numbers of unique subscription users/ pay perview users,
	@RequestMapping("/uniqueSubscritpionUsers/{PaidStatus}")
	public  List<User> uniqueSubscriptionUsers(@PathVariable PaidStatus paidStatus){
		List<User> uniqueSubusers=reportservice.getAllUniqueSubscriptionUsers(paidStatus );
		return uniqueSubusers;	
	}


	//	total unique users who played atleast a movie in month
	@RequestMapping("/totalUniqueActiveUsers")
	public  List<User> totalUniqueActiveUsers(){
		List<User> uniqueSubusers=reportservice.getAllUniqueActiveusers();
		return uniqueSubusers;	
	}


	//Not finished still
	@RequestMapping("/subscriptionIncomeMonth/{PaidStatus}")
	public int monthlySubscriptionIncome(@PathVariable PaidStatus paidStatus){
		int totalIncome=reportservice.getIncomeBasedonSubscription(paidStatus);
		return totalIncome;	
	}



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



}


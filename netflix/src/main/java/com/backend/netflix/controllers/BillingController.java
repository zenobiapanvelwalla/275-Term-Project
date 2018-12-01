package com.backend.netflix.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.backend.netflix.vo.Billing;
import com.backend.netflix.vo.UserSubscription;
import com.backend.netflix.repository.BillingRepository;
import com.backend.netflix.repository.UserSubscriptionRepository;
import com.backend.netflix.services.BillingService;
import com.backend.netflix.services.MovieService;
import com.backend.netflix.services.UserService;
import com.backend.netflix.services.UserSubscriptionService;
import com.backend.netflix.vo.Movie;
import com.backend.netflix.vo.User;

import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class BillingController {

	@Autowired
	private BillingService billingService ;
	
	@Autowired
	private UserSubscriptionService usService;
	
	@RequestMapping("/users/billingStatus")
	public String getStatus(User user) {
		int uid=user.getId();
		Billing billing=billingService.findByUserId(uid);
		return billing.getPstatus().toString();
		
		
	}
	
	
	@RequestMapping("/users/enddate")
	public Date renewalDate(User user) {
		int uid=user.getId();
		UserSubscription userSubscription=null;
		UserSubscription userSubsription=usService.findByUserId(uid);
		Date endDate = new Date();
		if(userSubsription!=null) {
			endDate = userSubscription.getEndDate();
		}
		return endDate;
	}
	
}
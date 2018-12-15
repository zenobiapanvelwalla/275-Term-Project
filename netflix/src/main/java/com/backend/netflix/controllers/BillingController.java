package com.backend.netflix.controllers;

import java.util.*;

import com.backend.netflix.services.*;
import com.backend.netflix.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.backend.netflix.repository.BillingRepository;
import com.backend.netflix.repository.UserSubscriptionRepository;

import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin(origins = "http://ec2-34-220-9-51.us-west-2.compute.amazonaws.com:3000",allowCredentials="true")
//@CrossOrigin(origins = "http://localhost:3000",allowCredentials="true")
public class BillingController {

	@Autowired
	private BillingService billingService ;
	
	@Autowired
	private UserSubscriptionService usService;

	@Autowired
	private UserActivityService userActivityService;


	@PostMapping(path = "/pay")
	public ResponseEntity<?> AddBiiligDetailsForMovie(@RequestBody HashMap<String,String> data, HttpSession session) throws Exception {

		// *******************For testing ONLY  ***************
		//session.setAttribute("userId",1);

		HashMap<String, Object> response = new HashMap<>();
//		int userId =(int)session.getAttribute("userId");
		int userId = Integer.parseInt(data.get("userId"));
		System.out.println("------------------Add BILIING UserId:"+ userId);

		int movieId = Integer.parseInt(data.get("movieId"));
		int moneyPaid = Integer.parseInt(data.get("moneyPaid"));
		String paymentType = data.get("paymentType");
		billingService.addBillingDetails(userId, movieId, moneyPaid, paymentType);
		//pass the checkpoint as 0, since the user has not begun watching the movie
		userActivityService.addUserActivity(userId,movieId,0);

		List<Integer> moviesPaidForList =  billingService.getListOfMoviesUserHasPaidFor(userId);

		response.put("success", true);
		response.put("message", "Billing done for Pay per view Movie Successfully");
		response.put("moviesPaidForList", moviesPaidForList);
		response.put("statusCode", 200);

		return new ResponseEntity(response, HttpStatus.CREATED);
	}

}
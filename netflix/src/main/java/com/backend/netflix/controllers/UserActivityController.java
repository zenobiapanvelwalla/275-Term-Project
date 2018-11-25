package com.backend.netflix.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.netflix.services.UserActivityService;
import com.backend.netflix.vo.User;
import com.backend.netflix.vo.UserActivity;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserActivityController {
	@Autowired
	private UserActivityService userActivityService;
	
	@RequestMapping("/user-activities/{user_id}")
	public List<UserActivity> getAllUserActitities(@PathVariable int user_id) {
		//return "Hello";
		
		return userActivityService.getUserActivityByUserId(user_id);
		
		
	}
}

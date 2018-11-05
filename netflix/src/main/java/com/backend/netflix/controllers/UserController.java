package com.backend.netflix.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.netflix.services.UserService;
import com.backend.netflix.vo.User;

@RestController
public class UserController {
	@Autowired
	private UserService userService ;
	
	@RequestMapping("/users")
	public List<User> getAllUsers() {
		//return "Hello";
		
		return userService.getAllUsers();
		
		
	}
}

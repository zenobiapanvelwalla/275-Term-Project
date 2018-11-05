package com.backend.netflix.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.netflix.vo.User;
import com.backend.netflix.interfaces.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		System.out.println("Users size:-"+users.size());
		return users;
	}

}

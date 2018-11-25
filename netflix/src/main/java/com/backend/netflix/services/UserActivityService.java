package com.backend.netflix.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.netflix.vo.UserActivity;
import com.backend.netflix.repository.UserActivityRepository;

@Service
public class UserActivityService {
	@Autowired
	private UserActivityRepository usRepo;
	public List<UserActivity> getUserActivityByUserId(int userId){
		return usRepo.findByUserId(userId);
	}
}

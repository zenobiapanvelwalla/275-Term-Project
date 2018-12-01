package com.backend.netflix.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.netflix.vo.User;
import com.backend.netflix.repository.UserRepository;



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
	
	
    public void addUser(User user) {
		
		userRepository.save(user);
	}
    public User getUser(int id) {
		return userRepository.findById(id);
	}
    
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public List<User> login(User user) throws Exception {
		
		List<User> s=userRepository.findByEmailAndPassword(user.getEmail(),user.getPassword());
		return s;
		
	}


	public void setVerifiedToTrue(int id) {
		userRepository.setVerifiedToTrue(id);
	}


	public List<User> findByEmailAndPassword(String email, String password) {
		List<User> s=userRepository.findByEmailAndPassword(email,password);
		return s;
	}
    

}

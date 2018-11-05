package com.backend.netflix.services;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.backend.netflix.interfaces.UserRepository;
import com.backend.netflix.vo.CustomUserDetail;
import com.backend.netflix.vo.User;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserDetails dt = null;
		Optional<User> users = userRepository.findByEmail(email);
		if(!users.isPresent()) {
			throw new UsernameNotFoundException("Username not found");
			
		} else {
			System.out.println(users.get().getEmail());
		   dt =  users.map(CustomUserDetail::new).get();
		   System.out.println("Mapped to user detials "+ dt.getUsername());
		}
		
		return dt;
	}

	
}

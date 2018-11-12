package com.backend.netflix.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.backend.netflix.vo.User;

@Repository("userRepository")
public interface UserRepository extends CrudRepository<User, Integer> {
	
	List<User> findByEmailAndPassword(String email,String password);
	User findByEmail(String email);

}

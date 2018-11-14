package com.backend.netflix.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.backend.netflix.beans.User;

public interface UserRepository extends CrudRepository<User, Integer> {

	User findByEmail(String email);
	List<User> findByEmailAndPassword(String email,String password);
	
	
	Optional<User> findByDisplayName(String displayName);
	

	Optional<User> findById(String id);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE users SET verified=true WHERE id=?1,",nativeQuery=true)
	public void setVerifiedToTrue(int id);

}

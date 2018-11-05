package com.backend.netflix.interfaces;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.backend.netflix.vo.User;

public interface UserRepository extends CrudRepository<User, Integer> {

	Optional<User> findByEmail(String email);

}

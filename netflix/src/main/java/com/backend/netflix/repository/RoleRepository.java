package com.backend.netflix.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.backend.netflix.vo.User;

public interface RoleRepository extends CrudRepository<User, Integer> {

	Optional<User> findByEmail(String email);

}

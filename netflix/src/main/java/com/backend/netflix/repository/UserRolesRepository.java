package com.backend.netflix.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import com.backend.netflix.vo.User;
import com.backend.netflix.vo.UserRoles;

public interface UserRolesRepository extends CrudRepository<UserRoles, Integer> {

	Set<UserRoles> findById(String id);

}

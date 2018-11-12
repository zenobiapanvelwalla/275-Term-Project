package com.backend.netflix.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import com.backend.netflix.vo.User;
import com.backend.netflix.beans.UserActivity;
import com.backend.netflix.vo.UserRoles;

public interface UserActivityRepository extends CrudRepository<User, Integer> {

//	Set<UserActivity> findByUserid(String  userid);


}

package com.backend.netflix.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import com.backend.netflix.beans.User;
import com.backend.netflix.beans.UserActivity;


public interface UserActivityRepository extends CrudRepository<UserActivity, Integer> {

   Set<UserActivity> findByUserId(String userid);


}

package com.backend.netflix.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import com.backend.netflix.vo.User;
import com.backend.netflix.vo.UserActivity;


public interface UserActivityRepository extends CrudRepository<UserActivity, Integer> {

   public List<UserActivity> findByUserId(int userid);


}

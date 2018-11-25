package com.backend.netflix.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.backend.netflix.vo.User;
import com.backend.netflix.vo.UserActivity;
import org.springframework.transaction.annotation.Transactional;


public interface UserActivityRepository extends CrudRepository<UserActivity, Integer> {

   public List<UserActivity> findByUserId(int userid);

   @Transactional
   @Query(value = "SELECT * FROM users_activities WHERE user_id=? and movie_id = ? ORDER BY created_at DESC", nativeQuery = true)
   public List<UserActivity> findByUserIdAndMovieId(int userId,int MovieId);


   @Transactional
   @Query(value = "SELECT * FROM users_activities WHERE user_id=? ORDER BY updated_at DESC", nativeQuery = true)
   List<UserActivity> findByUserIdOrderByUpdatedAt(int userId);


}

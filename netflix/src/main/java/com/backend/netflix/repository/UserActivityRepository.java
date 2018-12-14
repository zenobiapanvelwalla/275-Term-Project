package com.backend.netflix.repository;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.backend.netflix.vo.Movie;
import com.backend.netflix.vo.MovieCount;
import com.backend.netflix.vo.User;
import com.backend.netflix.vo.UserActivity;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserActivityRepository extends CrudRepository<UserActivity, Integer> {

   List<UserActivity> findByUserId(int userid);

   @Transactional
   @Query(value = "SELECT * FROM users_activities WHERE user_id=? and movie_id = ? ORDER BY created_at DESC", nativeQuery = true)
   public List<UserActivity> findByUserIdAndMovieId(int userId,int MovieId);

   @Query(value = "select count(*) from users_activities where movie_id=? and checkpoint > 0 and TIMESTAMPDIFF(HOUR,updated_at, now())<=24;",nativeQuery = true)
   public int getNumberOfPlaysForMovieInTwentyFourHours(int movieId);
   
   @Query(value = "select count(*) from users_activities where movie_id=? and checkpoint > 0 and DATEDIFF(now(),updated_at)<=7;",nativeQuery= true)
   public int getNumberOfPlaysForMovieInAWeek(int movieId);
   
   @Query(value = "select count(*) from users_activities where movie_id=? and checkpoint > 0 and DATEDIFF(now(),updated_at)<=30;",nativeQuery= true)
   public int getNumberOfPlaysForMovieInAMonth(int movieId);

   /*------TOP 10 Movies----------*/
   @Transactional
   @Query(value="select movie_id from users_activities where checkpoint > 0 and TIMESTAMPDIFF(HOUR,updated_at, now())<=24 GROUP BY movie_id order by count(*) DESC LIMIT 10;",nativeQuery=true)
   public List<Integer> getTopTenMoviesInTwentyFourHours();
   
   @Transactional
   @Query(value="select COUNT(*) from users_activities where checkpoint > 0 and TIMESTAMPDIFF(HOUR,updated_at, now())<=24 GROUP BY movie_id order by count(*) DESC LIMIT 10;",nativeQuery=true)
   public List<BigInteger> getTopTenMoviesPlayCountsInTwentyFourHours();
   
   @Transactional
   @Query(value="select movie_id from users_activities where checkpoint > 0 and DATEDIFF(now(),updated_at)<=7 GROUP BY movie_id ORDER BY count(*) DESC LIMIT 10;",nativeQuery=true)
   public List<Integer> getTopTenMoviesInWeek();
   
   @Transactional
   @Query(value="select count(*) as playCount from users_activities where checkpoint > 0 and DATEDIFF(now(),updated_at)<=7 GROUP BY movie_id ORDER BY playCount DESC LIMIT 10;",nativeQuery=true)
   public List<BigInteger> getTopTenMoviesPlayCountsInAWeek();
   
   

   @Transactional
   @Query(value="select count(*) as playCount from users_activities where checkpoint > 0 and DATEDIFF(now(),updated_at)<=30 GROUP BY movie_id ORDER BY playCount DESC LIMIT 10;",nativeQuery=true)
   public List<BigInteger> getTopTenMoviesPlayCountsInAMonth();
   
   @Transactional
   @Query(value="select movie_id from users_activities where checkpoint > 0 and DATEDIFF(now(),updated_at)<=30 GROUP BY movie_id ORDER BY count(*) DESC LIMIT 10;",nativeQuery=true)
   public List<Integer> getTopTenMoviesInAMonth();



   @Transactional
   @Query(value = "SELECT * FROM users_activities WHERE user_id=? AND checkpoint>0 ORDER BY updated_at DESC", nativeQuery = true)
   List<UserActivity> findByUserIdOrderByUpdatedAt(int userId);

   /*------TOP 10 Users----------*/
   @Transactional
   @Query(value="select user_id from users_activities where checkpoint > 0 and TIMESTAMPDIFF(HOUR,updated_at, now())<=24 GROUP BY user_id order by count(*) DESC LIMIT 10;",nativeQuery=true)
   List<Integer> getTopTenUsersInTwentyFourHours();


   @Transactional
   @Query(value="select COUNT(*) from users_activities where checkpoint > 0 and TIMESTAMPDIFF(HOUR,updated_at, now())<=24 GROUP BY user_id order by count(*) DESC LIMIT 10;",nativeQuery=true)
   List<BigInteger> getTopTenUsersPlayCountsInTwentyFourHours();


   @Transactional
   @Query(value="select count(*) as playCount from users_activities where checkpoint > 0 and DATEDIFF(now(),updated_at)<=7 GROUP BY user_id ORDER BY playCount DESC LIMIT 10;",nativeQuery=true)
   List<BigInteger> getTopTenUsersPlayCountsInAWeek();

   @Transactional
   @Query(value="select user_id from users_activities where checkpoint > 0 and DATEDIFF(now(),updated_at)<=7 GROUP BY user_id ORDER BY count(*) DESC LIMIT 10;",nativeQuery=true)
   List<Integer> getTopTenUsersInWeek();

   @Transactional
   @Query(value="select count(*) as playCount from users_activities where checkpoint > 0 and DATEDIFF(now(),updated_at)<=30 GROUP BY user_id ORDER BY playCount DESC LIMIT 10;",nativeQuery=true)
   List<BigInteger> getTopTenUsersPlayCountsInAMonth();

   @Transactional
   @Query(value="select user_id from users_activities where checkpoint > 0 and DATEDIFF(now(),updated_at)<=30 GROUP BY user_id ORDER BY count(*) DESC LIMIT 10;",nativeQuery=true)
   List<Integer> getTopTenUsersInAMonth();
   
   @Query(value="SELECT * FROM users_activities WHERE user_id=? AND movie_id=? ORDER BY updated_at DESC LIMIT 1",nativeQuery=true)
   UserActivity findLatestByUserIdAndMovieId(int userId, int movieId);
   
//   @Transactional
//   @Query(value="select user_id from users_activities",nativeQuery=true)
//   List<Integer> getUniqueActiveusers();
   
   
  //next two functions for -- total unique active users (those who played at least one movie in the month)
   @Transactional
   @Query(value="SELECT COUNT(DISTINCT user_id) FROM users_activities WHERE\r\n" + 
   		"   TIMESTAMPDIFF(MONTH,updated_at, now())<12 AND checkpoint>0 GROUP BY month(updated_at),year(updated_at)\r\n" +
   		"   ORDER BY year(updated_at) DESC, month(updated_at) DESC",nativeQuery=true)
   List<BigInteger> getUsersWatchedMovedPerMonth();
   
   @Transactional
   @Query(value="SELECT month(updated_at) FROM users_activities WHERE\r\n" + 
   		"   TIMESTAMPDIFF(MONTH,updated_at, now())<12 AND checkpoint>0 GROUP BY month(updated_at),year(updated_at)\r\n" +
   		"   ORDER BY year(updated_at) DESC, month(updated_at) DESC",nativeQuery=true)
   List<Integer> getMonthsWatchedMovedPerMonth();
   
   //Zenobia
   @Transactional
   @Query(value="SELECT * FROM movies WHERE id IN (SELECT DISTINCT movie_id FROM users_activities WHERE user_id=? AND isWatching=true AND isWatched=false",nativeQuery=true)
   List<Movie> getMoviesInProgress(int userId);
   //End - Zenobia
   
   
}

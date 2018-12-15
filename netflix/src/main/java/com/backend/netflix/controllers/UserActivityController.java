package com.backend.netflix.controllers;

import java.util.HashMap;
import java.util.List;

//import com.backend.netflix.beans.TopUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.backend.netflix.services.UserActivityService;
import com.backend.netflix.vo.Movie;
import com.backend.netflix.vo.UserActivity;
import com.backend.netflix.beans.TopMovie;
import com.backend.netflix.beans.TopUser;

@RestController
@CrossOrigin(origins = "http://ec2-34-220-9-51.us-west-2.compute.amazonaws.com:3000",allowCredentials="true")
public class UserActivityController {
	@Autowired	
	private UserActivityService userActivityService;
	/*
	@RequestMapping("/user-activities/{user_id}")
	public List<UserActivity> getAllUserActivities(@PathVariable int user_id) {
		//return "Hello";
		
		return userActivityService.getUserActivityByUserId(user_id);
	}
	*/

	//Get movie playing history in reverse chronological order of given customer
	@RequestMapping(value="/user-activities/movie-playing-history/{userId}",method=RequestMethod.GET)
	public ResponseEntity<?> getMoviePlayingHistory(@PathVariable int userId){
		HashMap<String, Object> response = new HashMap<String, Object>();
		List<UserActivity> history = userActivityService.getUserActivityByUserIdOrderByUpdatedAt(userId);
		response.put("success", true);
		response.put("history",history);
		response.put("statusCode", 200);
		return new ResponseEntity(response, HttpStatus.OK);
	}
	
	
	//added checkpoint to updated the checkpoint of an already existing user activity
	@RequestMapping(value = "/user-activities/store/{user_id}/{movie_id}/{checkpoint}",method = RequestMethod.GET)
	public ResponseEntity<?> addUserActivity(@PathVariable int user_id, @PathVariable int movie_id,@PathVariable long checkpoint) throws Exception {

		HashMap<String, Object> response = new HashMap<>();

		//Saving user activity
		userActivityService.addUserActivity(user_id,movie_id,checkpoint);

		response.put("success", true);
		response.put("message", "Activity Added Successfully");
		response.put("statusCode", 200);

		return new ResponseEntity(response, HttpStatus.CREATED);
	}
	// rough code in progress...
//	@RequestMapping(value="/user-activities/update/{user_id}/{movie_id}",method=RequestMethod.GET)
//	public ResponseEntity<?> addOrUpdateUserActivity(@PathVariable int user_id,@PathVariable int movie_id) throws Exception{
//		HashMap<String,Object> response = new HashMap<String, Object>();
//		userActivityService.addOrUpdateUserActivity(user_id,movie_id);
//		return new ResponseEntity(response,HttpStatus.OK);
//	}
	@RequestMapping(value="/user-activities-update/{user_id}/{movie_id}/{checkpoint}")
	public ResponseEntity<?> updateUserActivity(@PathVariable int user_id, @PathVariable int movie_id,@PathVariable long checkpoint){
		HashMap<String, Object> response = new HashMap<>();

		//Saving user activity
		userActivityService.updateUserActivity(user_id,movie_id,checkpoint);

		response.put("success", true);
		response.put("message", "Activity Added Successfully");
		response.put("statusCode", 200);

		return new ResponseEntity(response, HttpStatus.ACCEPTED);
	}
	@GetMapping("/movies/play-count/{movieId}/{type}")
	public ResponseEntity<?> getNumberOfPlaysForMovie(@PathVariable int movieId,@PathVariable int type){
		HashMap<String, Object> response = new HashMap<String, Object>();
		int playCount = userActivityService.getNumberOfPlaysForMovie(movieId,type);
		response.put("success", true);
		response.put("message", playCount);
		response.put("statusCode", 200);
		return new ResponseEntity(response, HttpStatus.OK);
	}
	
	@GetMapping("/movies/top-ten/{type}")
	public ResponseEntity<?> getTopTenMovies(@PathVariable int type){
		HashMap<String, Object> response = new HashMap<String, Object>();
		List<TopMovie> topTenMovies = userActivityService.getTopTenMovies(type);
		response.put("success", true);
		response.put("message", topTenMovies);
		response.put("statusCode", 200);
		return new ResponseEntity(response, HttpStatus.OK);
	}

	@GetMapping("/users/top-ten/{type}")
	public ResponseEntity<?> getTopTenUsers(@PathVariable int type){
		HashMap<String, Object> response = new HashMap<String, Object>();
		List<TopUser> topTenUsers = userActivityService.getTopTenUsers(type);
		response.put("success", true);
		response.put("message", topTenUsers);
		response.put("statusCode", 200);
		return new ResponseEntity(response, HttpStatus.OK);
	}
	
	@GetMapping("/user-activities/get-movies-in-progress/{userId}")
	public ResponseEntity<?> getMoviesInProgress(@PathVariable int userId){
		HashMap<String, Object> response = new HashMap<String, Object>();
		List<Movie> movies = userActivityService.getMoviesInProgress(userId);
		
		response.put("success", true);
		response.put("statusCode",200);
		response.put("movies",movies);
		return new ResponseEntity(response, HttpStatus.OK);
	}

}

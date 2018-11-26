package com.backend.netflix.controllers;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.backend.netflix.services.UserActivityService;
import com.backend.netflix.vo.UserActivity;
import com.backend.netflix.beans.TopMovie;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserActivityController {
	@Autowired
	private UserActivityService userActivityService;
	/*
	@RequestMapping("/user-activities/{user_id}")
	public List<UserActivity> getAllUserActitities(@PathVariable int user_id) {
		//return "Hello";
		
		return userActivityService.getUserActivityByUserId(user_id);
	}
	*/

	@RequestMapping("/user-activities/{user_id}")
	public List<UserActivity> getUseractivityInReverseOrder(@PathVariable int user_id) {
		return userActivityService.getUserActivityByUserIdOrderByUpdatedAt(user_id);
	}

	@RequestMapping(value = "/user-activities/store/{user_id}/{movie_id}",method = RequestMethod.GET)
	public ResponseEntity<?> addUserActivity(@PathVariable int user_id, @PathVariable int movie_id) throws Exception {

		HashMap<String, Object> response = new HashMap<>();

		//Saving user activity
		userActivityService.addUserActivity(user_id,movie_id);

		response.put("success", true);
		response.put("message", "Activity Added Successfully");
		response.put("statusCode", 200);

		return new ResponseEntity(response, HttpStatus.CREATED);
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

}

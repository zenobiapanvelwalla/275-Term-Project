package com.backend.netflix.controllers;


import com.backend.netflix.beans.HighRatedMovie;
import com.backend.netflix.services.ReviewService;
import com.backend.netflix.services.UserActivityService;
import com.backend.netflix.vo.Movie;
import com.backend.netflix.vo.Review;
import com.backend.netflix.vo.UserActivity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://ec2-34-220-9-51.us-west-2.compute.amazonaws.com:3000",allowCredentials="true")
public class ReviewController {

    @Autowired
    ReviewService reviewService;
    @Autowired
    UserActivityService uaService;

    @GetMapping("/movies/highly-rated")
    public ResponseEntity<?> getTopTenMoviesByStarRating(){
        HashMap<String, Object> response = new HashMap<String, Object>();
        List<HighRatedMovie> highRatedMovies = reviewService.getTopTenMoviesByStarRating();
        response.put("success", true);
        response.put("message", highRatedMovies);
        response.put("statusCode", 200);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping(path = "/movies/add-review", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addReview(@RequestBody Review review) throws Exception {

        //Check if user has already given review from front end
        HashMap<String, Object> response = new HashMap<>();
        List<UserActivity> uaList = uaService.getUserActivityList(review.getUserId(),review.getMovieId());
        if(uaList.size()>0){
            //Saving user reviews
            Movie updatedMovie = reviewService.addReview(review);
            List<Review> reviews = reviewService.getAllReviewsFoMovieId(review.getMovieId());
            response.put("success", true);
            response.put("message", "Review Added Successfully");
            response.put("updatedMovie",updatedMovie);
            response.put("reviews",reviews);
            response.put("statusCode", 200);
        } else {
            response.put("success", false);
            response.put("message", "You cannot add a review without watching the movie.");
            response.put("statusCode", 400);
        }


        return new ResponseEntity(response, HttpStatus.CREATED);
    }
}

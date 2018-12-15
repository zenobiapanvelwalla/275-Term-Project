package com.backend.netflix.controllers;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import com.backend.netflix.vo.Encryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.backend.netflix.vo.UserSubscription;
import com.backend.netflix.services.MovieService;
import com.backend.netflix.services.UserActivityService;
import com.backend.netflix.services.UserSubscriptionService;
import com.backend.netflix.vo.Movie;
import com.backend.netflix.vo.UserActivity;

import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin(origins = "http://ec2-34-220-9-51.us-west-2.compute.amazonaws.com:3000",allowCredentials="true")
//@CrossOrigin(origins = "http://localhost:3000",allowCredentials="true")
public class MovieController {

    @Autowired
    private MovieService movieService;
    
    @Autowired
    private UserActivityService uaService;
    
    @Autowired
    private UserSubscriptionService subService;

    @RequestMapping("/movies")
    public List<Movie> getAllMovies() {

        return movieService.getAllMovies();

    }
    
    
    @RequestMapping("/get-movies-for-customer/")
    public ResponseEntity<?> getMoviesForCustomer(HttpSession session){

        /************For TESTING ONLY**********/
        //session.setAttribute("userId",1);
    	int userId = (int)session.getAttribute("userId");
    	System.out.println("doooooooooooooooooooooooooooo"+ userId);
    	HashMap<String, Object> response = new HashMap<>();
    	List<Movie> movies = new ArrayList<Movie>();
    	List<String> availability = new ArrayList<String>();
    	availability.add("Free");
    	availability.add("PayPerViewOnly");
    	availability.add("Paid");
    	//check if user has a valid SubscriptionOnly
    	List<UserSubscription> subscriptionList = subService.findLatestSubscriptionByUserId(userId);
    	if(subscriptionList.size()>0) {
            UserSubscription subscription = subscriptionList.get(0);
            LocalDateTime endDate = subscription.getEndDate();
            LocalDateTime now = LocalDateTime.now();
            if (endDate.compareTo(now) > 0) {
                System.out.println("Subscription valid.");
                availability.add("SubscriptionOnly");
                for(int i=0;i<availability.size();i++) {
                    movies.addAll(movieService.getAllMoviesCustomer(availability.get(i)));
                }
            } else {
                System.out.println("Subscription not present or is invalid");
                for(int i=0;i<availability.size();i++) {
                    movies.addAll(movieService.getAllMoviesCustomer(availability.get(i)));
                }

            }
        }else  {
            System.out.println("Subscription not present or is invalid");
            for(int i=0;i<availability.size();i++) {
                movies.addAll(movieService.getAllMoviesCustomer(availability.get(i)));
            }
        }
    	response.put("success",true);
    	response.put("statusCode", 200);
    	response.put("message",movies);
    	return new ResponseEntity(response, HttpStatus.OK);
    }

    @RequestMapping("/movies/{movieId}")
    public ResponseEntity<?> getMovie(@PathVariable int movieId,HttpSession session){
    	/*for testing*/
//    	session.setAttribute("userId",1);
//    	session.setAttribute("role", "ADMIN");
    	/*END TEESTING*/
    	Movie movie = movieService.getMovie(movieId);
    	System.out.println("USER ID"+ session.getAttribute("userId"));
    	System.out.println("USER ROLE"+session.getAttribute("role"));
    	HashMap<String, Object> response = new HashMap<>();
    	boolean canUserWatch = false;
    	boolean canUserReview = false;
        System.out.println("------------------Get Movies UserId:"+ session.getAttribute("userId"));
        System.out.println("------------------Get MOvies role:"+ session.getAttribute("role"));
    	//
        //check if movie type is pay per view--> if user has Paid for movie
        //if movie type is Paid, unsubscribed user should have Paid for movie
    	if(session.getAttribute("role").toString().compareTo("CUSTOMER")==0) {
            int userId = (int) session.getAttribute("userId");
            UserActivity userActivity = uaService.getLatestUserActivityByUserIdAndMovieId(userId, movieId);
            if (userActivity == null) {
                if (movie.getIsDeleted()) {
                    canUserWatch = false;
                } else
                    canUserWatch = true;
                //if user activity is null->user has not started/finished watching->cannot review
                canUserReview = false;
            } else {
            	
                if (movie.getIsDeleted()) {
                    if (userActivity.isWatching())
                        canUserWatch = true;
                    if (userActivity.isWatched())
                        canUserWatch = false;
                }
                else
                    canUserWatch = true;
                //if user activity checkpoint time is greater than 00:00 than he can review
                //LocalTime checkpoint = userActivity.getCheckpoint();
                long checkpoint = userActivity.getCheckpoint();
                //if(checkpoint.compareTo(LocalTime.MIN)==0) {
                if(checkpoint==0) {
                	canUserReview= false;
                } else {
                	canUserReview= true;
                }
            }

    	} else {
    		if(movie.getIsDeleted()) {
    			canUserWatch = false;
    		}else {

    			canUserWatch = true;
    		}
    		//If user is admin, he can't review the movie
    		canUserReview = false;
    		
    	}
    	
    	response.put("canUserWatch",canUserWatch);
    	response.put("canUserReview", canUserReview);
    	response.put("success", true);
        response.put("message", movie);
        response.put("statusCode", 200);
        
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @RequestMapping("/admin/movies")
    public ResponseEntity<?> getAllMoviesForAdmin(HttpSession session) throws Exception {
        HashMap<String, Object> response = new HashMap<>();
        //Remove the following line
       session.setAttribute("role","ADMIN");

        if (session.getAttribute("role").toString().compareTo("ADMIN")==0) {
            List<Movie> movieList = movieService.getAllMoviesForAdmin();
            response.put("success", true);
            response.put("message", movieList);
            response.put("statusCode", 200);
        }else {
            response.put("success", false);
            response.put("message", "You are not authorized.");
            response.put("statusCode", 500);
        }


        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @PostMapping(path = "/movies/store", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addMovie(@RequestBody Movie movie) throws Exception {

        HashMap<String, Object> response = new HashMap<>();
        Movie existingMovie = new Movie();
        existingMovie = movieService.getMovieByTitle(movie.getTitle());
        if(existingMovie!=null) {
        	response.put("success", false);
            response.put("message", "Movie Already Exists!");
            response.put("statusCode", 400);

            return new ResponseEntity(response, HttpStatus.IM_USED);
        }

        //Saving movie
        movieService.addMovie(movie);

        response.put("success", true);
        response.put("message", "Movie Added Successfully");
        response.put("statusCode", 200);

        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/movies/update/{movieId}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateMovie(@PathVariable int movieId, @RequestBody Movie movie) throws Exception {

        HashMap<String, Object> response = new HashMap<>();

        //updating movie
        movieService.updateMovie(movieId,movie);

        response.put("success", true);
        response.put("message", "Movie Updated Successfully");
        response.put("statusCode", 200);

        return new ResponseEntity(response, HttpStatus.CREATED);

    }

    @RequestMapping(value="/movies/delete/{movieId}",method=RequestMethod.DELETE)
    public ResponseEntity<?> deleteMovie(@PathVariable int movieId){

        HashMap<String, Object> response = new HashMap<>();
        try{
            movieService.deleteMovie(movieId);

            response.put("success", true);
            response.put("message", "Movie Deleted Successfully");
            response.put("statusCode", 200);


        } catch(Exception e){
            response.put("success", false);
            response.put("message", "Movie could not be deleted!");
            response.put("statusCode", 500);
        }
        return   new ResponseEntity(response, HttpStatus.OK);

    }
    //genre,actors, directors,mpaa rating, number of stars (from front end only), year (from front end users only)
    @GetMapping("/movies/get-unique-movie-attributes")
    public ResponseEntity<?> getDirector(HttpSession session){
    	List<String> directors = movieService.getUniqueDirectors();
    	List<String> actors= movieService.getUniqueActors();
    	List<String> genres = movieService.getUniqueGenres();
    	List<String> mpaa = movieService.getUniqueMPAARatings();
    	System.out.println("MPPPPPPPPPPPPPPPPPAA " + mpaa);
    	HashMap<String,Object> response = new HashMap<String,Object>();
    	response.put("success", true);
    	response.put("statusCode",200);
    	response.put("directors",directors);
    	response.put("actors",actors);
    	response.put("genres",genres);
    	response.put("mpaa",mpaa);
    	return   new ResponseEntity(response, HttpStatus.OK);
    }
    
    @GetMapping("/movies/recommend-movies/{movieId}")
    public ResponseEntity<?> recommendMovies(@PathVariable int movieId){
    	HashMap<String,Object> response = new HashMap<String,Object>();
    	List<Movie> recommendedMoviesBasedOnDirector = movieService.recommendMoviesBasesOnDirector(movieId);
    	//List<Movie> recommededMoviesBasedOnActors =  movieService.recommendMoviesBasesOnActor(movieId);
    	List<Movie> recommededMoviesBasedOnGenre =  movieService.recommendMoviesBasesOnGenre(movieId);
    	
    	response.put("basedOnDirector",recommendedMoviesBasedOnDirector);
    	//response.put("basedOnActor",recommededMoviesBasedOnActors);
    	response.put("basedOnGenre",recommededMoviesBasedOnGenre);
    	return new ResponseEntity(response, HttpStatus.OK);
    }
}
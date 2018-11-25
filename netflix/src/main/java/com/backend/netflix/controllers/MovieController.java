package com.backend.netflix.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import com.backend.netflix.vo.Encryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.backend.netflix.services.MovieService;
import com.backend.netflix.vo.Movie;
import com.backend.netflix.vo.User;

@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;

    @RequestMapping("/movies")
    public List<Movie> getAllMovies() {

        return movieService.getAllMovies();

    }

    @PostMapping(path = "/movies/store", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addMovie(@RequestBody Movie movie) throws Exception {

        HashMap<String, Object> response = new HashMap<>();

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
}
package com.backend.netflix.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}

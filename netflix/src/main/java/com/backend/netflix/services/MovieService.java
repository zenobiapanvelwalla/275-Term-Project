package com.backend.netflix.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.netflix.repository.MovieRepository;
import com.backend.netflix.vo.Movie;
import com.backend.netflix.vo.User;

@Service
public class MovieService {
	
	@Autowired
	private MovieRepository movieRepository;
	
	public List<Movie> getAllMovies() {
		List<Movie> movies = new ArrayList<>();
		movieRepository.findAll().forEach(movies::add);
		System.out.println("Movies size:-"+movies.size());
		return movies;
	}

	public void addMovie(Movie movie) {
		movieRepository.save(movie);
	}
}

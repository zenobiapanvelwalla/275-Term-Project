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

	public void updateMovie(int movieId, Movie movie) {
		Movie movie1 = movieRepository.findById(movieId);
		movie1.setTitle(movie.getTitle());
		movie1.setActors(movie.getActors());
		movie1.setAvailability(movie.getAvailability());
		movie1.setCountry(movie.getCountry());
		movie1.setDirector(movie.getDirector());
		movie1.setGenre(movie.getGenre());
		movie1.setImageUrl(movie.getImageUrl());
		movie1.setIsDeleted(movie.getIsDeleted());
		movie1.setNoOfPlays(movie.getNoOfPlays());
		movie1.setPrice(movie.getPrice());
		movie1.setMovieUrl(movie.getMovieUrl());
		movie1.setNoOfStars(movie.getNoOfStars());
		movie1.setRating(movie.getRating());
		movie1.setStudio(movie.getStudio());
		movie1.setSynopsis(movie.getSynopsis());
		movie1.setYear(movie.getYear());

		movieRepository.save(movie1);
	}

	public void deleteMovie(int movieId){
		Movie m =  movieRepository.findById(movieId);
		m.setIsDeleted(true);
		movieRepository.save(m);
	}

	public List<Movie> getAllMoviesForAdmin(){
		return movieRepository.getAllMoviesByIsDeleted(false);
	}


}

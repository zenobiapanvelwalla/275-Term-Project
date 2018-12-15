package com.backend.netflix.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
		movie1.setAvgStarRating(movie.getAvgStarRating());
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
	
	public List<Movie> getAllMoviesCustomer(String availability){
		return movieRepository.getAllMoviesByAvailability(availability);
	}

	public Movie getMovie(int movieId) {
		Movie movie = movieRepository.findById(movieId);
		return movie;
	}

	public List<String> getUniqueDirectors() {
		// TODO Auto-generated method stub
		List<String> directors = movieRepository.getUniqueDirectors();
		return directors;
	}
	
	public List<String> getUniqueActors() {
		// TODO Auto-generated method stub
		List<String> actorsTemp = movieRepository.getUniqueActors();
		List<String> actorsNonUnique = new ArrayList<String>();
		List<String> actorsUnique = new ArrayList<String>();
		for(String act: actorsTemp) {
			actorsNonUnique.addAll(Arrays.asList(act.split("\\s*,\\s*")));
		}
		actorsUnique = actorsNonUnique.stream().distinct().collect(Collectors.toList());
		return actorsUnique;
	}
	
	public List<String> getUniqueGenres() {
		// TODO Auto-generated method stub
		List<String> genres = movieRepository.getUniqueGenres();
		return genres;
	}

	public Movie getMovieByTitle(String title) {
		// TODO Auto-generated method stub
		return movieRepository.findByTitle(title);
	}

	public List<String> getUniqueMPAARatings() {
		// TODO Auto-generated method stub
		List<String> mpaa = movieRepository.getUniqueMPAARatings();
		return mpaa;
	}

	public List<Movie> recommendMoviesBasesOnDirector(int movieId) {
		Movie m = movieRepository.findById(movieId);
		
		List<Movie> recommendedMoviesBasesOnDirector = movieRepository.recommendMoviesBasedOnDirector(m.getDirector().trim(),movieId);
		return recommendedMoviesBasesOnDirector;
	}
	
	public List<Movie> recommendMoviesBasesOnActor(int movieId) {
		Movie m = movieRepository.findById(movieId);
		List<String> actorsNonUnique = new ArrayList<String>();
		actorsNonUnique.addAll(Arrays.asList(m.getActors().split("\\s*,\\s*")));
		List<Movie> recommendedMoviesBasesOnActors = new ArrayList<>();
		for(String act: actorsNonUnique) {
			recommendedMoviesBasesOnActors.addAll(movieRepository.recommendMovieBasedOnActors(act.trim(),movieId));
		}
		
		return recommendedMoviesBasesOnActors;
	}
	
	public List<Movie> recommendMoviesBasesOnGenre(int movieId) {
		Movie m = movieRepository.findById(movieId);
		List<Movie> recommendedMoviesBasesOnGenre = movieRepository.recommedMovieBasedOnGenre(m.getGenre().trim(),movieId);
		return recommendedMoviesBasesOnGenre;
	}


}

package com.backend.netflix.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.backend.netflix.vo.Movie;
import com.backend.netflix.vo.User;


public interface MovieRepository extends CrudRepository<Movie, Integer> {

//	req-8-a search by name/actors/synopsis/directors/title-satisfied

	Movie findById(int movieId);
	List<Movie> getAllMoviesByIsDeleted(boolean isDeleted);
	
	@Query(value="SELECT * FROM movies WHERE availability IN ?",nativeQuery=true)
	List<Movie> getAllMoviesByAvailability(String availability[]);

	Set<Movie> findByTitleOrSynopsisOrActorsOrDirector(String title,String Synopsis,List<String> actors, List<String> directors);
	
	Set<Movie> findByGenreAndYear(String genre, Date year);
	
	Set<Movie> findByGenreAndActors(String genre,String Actors);
	
	Set<Movie> findByGenreAndDirector(String genre,String director);
	
//	Set<Movie> findByGenreAndNoOfStars(String genre,String noofstar);

	Set<Movie> findByGenreAndRating(String genre,String Rating);

	Set<Movie> findByYearAndActors(String Year,String Actors);

	Set<Movie> findByYearAndDirector(String Year,String Director);

	Set<Movie> findByYearAndRating(String Year,String Rating);
	
	@Query(value="SELECT DISTINCT director FROM movies",nativeQuery=true)
	List<String> getUniqueDirectors();
	
	@Query(value="SELECT DISTINCT actors FROM movies",nativeQuery=true)
	List<String> getUniqueActors();
	
	@Query(value="SELECT DISTINCT genre FROM movies",nativeQuery=true)
	List<String> getUniqueGenres();
	
	

//	Set<Movie> findByYearAndNoOfStars(String Year,String Noofstar);


}

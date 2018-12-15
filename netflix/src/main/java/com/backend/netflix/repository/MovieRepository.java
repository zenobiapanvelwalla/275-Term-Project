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
	
	@Query(value="SELECT * FROM movies WHERE availability=?",nativeQuery=true)
	List<Movie> getAllMoviesByAvailability(String availability);

	Set<Movie> findByTitleOrSynopsisOrActorsOrDirector(String title,String Synopsis,List<String> actors, List<String> directors);
	
	Set<Movie> findByGenreAndYear(String genre, Date year);
	
	Set<Movie> findByGenreAndActors(String genre,String Actors);
	
	Set<Movie> findByGenreAndDirector(String genre,String director);
	
//	Set<Movie> findByGenreAndNoOfStars(String genre,String noofstar);

	Set<Movie> findByGenreAndRating(String genre,String Rating);

	Set<Movie> findByYearAndActors(String Year,String Actors);

	Set<Movie> findByYearAndDirector(String Year,String Director);

	Set<Movie> findByYearAndRating(String Year,String Rating);
	
	@Query(value="SELECT DISTINCT director FROM movies WHERE director <> ''",nativeQuery=true)
	List<String> getUniqueDirectors();
	
	@Query(value="SELECT DISTINCT actors FROM movies WHERE actors <> ''",nativeQuery=true)
	List<String> getUniqueActors();
	
	@Query(value="SELECT DISTINCT genre FROM movies WHERE genre <> ''",nativeQuery=true)
	List<String> getUniqueGenres();
	
	Movie findByTitle(String title);
	
	@Query(value="SELECT DISTINCT rating FROM movies",nativeQuery=true)
	List<String> getUniqueMPAARatings();
	
	
	@Query(value="SELECT *  FROM movies WHERE director=? AND id <> ?;",nativeQuery=true)
	List<Movie> recommendMoviesBasedOnDirector(String director, int movieId);
	
	@Query(value="SELECT *  FROM movies WHERE actors LIKE '%?%' AND id <> ?;",nativeQuery=true)
	List<Movie> recommendMovieBasedOnActors(String actor,int movieId);
	
	@Query(value="SELECT *  FROM movies WHERE genre =? AND id<> ?;",nativeQuery=true)
	List<Movie> recommedMovieBasedOnGenre(String genre,int movieId);
	
	

//	Set<Movie> findByYearAndNoOfStars(String Year,String Noofstar);


}

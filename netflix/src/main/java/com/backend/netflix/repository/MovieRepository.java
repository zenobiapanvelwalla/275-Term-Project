package com.backend.netflix.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.backend.netflix.beans.Movie;
import com.backend.netflix.vo.User;
import com.backend.netflix.vo.UserRoles;

public interface MovieRepository extends CrudRepository<Movie, Integer> {

//	req-8-a search by name/actors/synopis/directors/title-satisfied
	Set<Movie> findByTitleOrSynopsisOrActorsOrDirector(String title,String Synopsis,List<String> actors, List<String> directors);
	
	Set<Movie> findByGenreAndYear(String genre, Date year);
	
	Set<Movie> findByGenreAndActors(String genre,String Actors);
	
	Set<Movie> findByGenreAndDirector(String genre,String director);
	
	Set<Movie> findByGenreAndNoofstar(String genre,String noofstar);

	Set<Movie> findByGenreAndRating(String genre,String Rating);

	Set<Movie> findByYearAndActors(String Year,String Actors);

	Set<Movie> findByYearAndDirector(String Year,String Director);

	Set<Movie> findByYearAndRating(String Year,String Rating);

	Set<Movie> findByYearAndNoofstar(String Year,String Noofstar);


}

package com.backend.netflix.beans;

import javax.persistence.ColumnResult;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.*;

import org.hibernate.annotations.NamedNativeQuery;

//@NamedNativeQuery(
//	    name = "MovieCount",
//	    query ="select u.movie_id,count(u) from users_activities u  where TIMESTAMPDIFF(HOUR,u.updated_at, now())<=24 GROUP BY u.movie_id order by count(u) DESC LIMIT 10;",
//	    resultSetMapping = "MovieCount"
//	)
//	@SqlResultSetMapping(
//	    name = "MovieCount",
//	    classes = @ConstructorResult(
//	        targetClass = MovieCount.class,
//	        columns = {
//	            @ColumnResult(name = "movieId"),
//	            @ColumnResult(name = "playCount")
//	        }
//	    )
//	)
public class MovieCount {
	private int movieId;
	private int playCount;
	
	public MovieCount() {}
	public MovieCount(int movieId, int playCount) {
		super();
		this.movieId = movieId;
		this.playCount = playCount;
	}
	
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	
	public int getPlayCount() {
		return playCount;
	}
	public void setPlayCount(int playCount) {
		this.playCount = playCount;
	}
	
	
	
	
	
	

}

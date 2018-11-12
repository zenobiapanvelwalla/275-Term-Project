package com.backend.netflix.vo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="user_activity")
public class UserActivity {
	
	
	private int userId;
	
	private List<String> watchedMovies;
	
	private List<String> watchingMovies;
	
	

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public List<String> getWatchedMovies() {
		return watchedMovies;
	}

	public void setWatchedMovies(List<String> watchedMovies) {
		this.watchedMovies = watchedMovies;
	}

	public List<String> getWatchingMovies() {
		return watchingMovies;
	}

	public void setWatchingMovies(List<String> watchingMovies) {
		this.watchingMovies = watchingMovies;
	}
	
	
	

}

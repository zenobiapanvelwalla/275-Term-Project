package com.backend.netflix.beans;

import java.util.List;

import org.springframework.context.annotation.Bean;


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
	
	
	public UserActivity(int userId, List<String> watchedMovies, List<String> watchingMovies) {
		super();
		this.userId = userId;
		this.watchedMovies = watchedMovies;
		this.watchingMovies = watchingMovies;
	}

	public List<String> getWatchingMovies() {
		return watchingMovies;
	}

	public void setWatchingMovies(List<String> watchingMovies) {
		this.watchingMovies = watchingMovies;
	}
	
//	Still have to add
	
	
}

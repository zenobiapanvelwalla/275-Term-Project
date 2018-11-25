package com.backend.netflix.vo;

import java.io.Serializable;
import java.sql.Date;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name="UserActivity")
@Table(name = "users_activities")
public class UserActivity{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private int userId;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="movie_id")
	private Movie movie;
	
	private boolean watched;
	private boolean watching;
	private Date checkpoint;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	public boolean isWatched() {
		return watched;
	}
	public void setWatched(boolean watched) {
		this.watched = watched;
	}
	public boolean isWatching() {
		return watching;
	}
	public void setWatching(boolean watching) {
		this.watching = watching;
	}
	public Date getCheckpoint() {
		return checkpoint;
	}
	public void setCheckpoint(Date checkpoint) {
		this.checkpoint = checkpoint;
	}
	
	
	

}

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

@Entity(name="UserMovie")
@Table(name = "users_movies")
public class UserMovie implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	
	@ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	//@ManyToOne
	private User user;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="movie_id")
	//@ManyToOne
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
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

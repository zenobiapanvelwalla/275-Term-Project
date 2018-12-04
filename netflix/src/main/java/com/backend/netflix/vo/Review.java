package com.backend.netflix.vo;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="reviews")
public class Review {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private int userId;
    private String userName;

//    public Movie getMovie() {
//        return movie;
//    }
//
//    public void setMovie(Movie movie) {
//        this.movie = movie;
//    }


    

	private int movieId;
    private int starRating;
    @Nullable
    private String reviewText;
    private LocalDateTime reviewTimeStamp= LocalDateTime.now();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }
    public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }


    @Nullable
    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(@Nullable String reviewText) {
        this.reviewText = reviewText;
    }

    public LocalDateTime getReviewTimeStamp() {
        return reviewTimeStamp;
    }

    public void setReviewTimeStamp(LocalDateTime reviewTimeStamp) {
        this.reviewTimeStamp = reviewTimeStamp;
    }
    public int getStarRating() {
        return starRating;
    }

    public void setStarRating(int starRating) {
        this.starRating = starRating;
    }



}

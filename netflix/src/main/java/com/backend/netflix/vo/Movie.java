
package com.backend.netflix.vo;

import com.backend.netflix.vo.User;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "movies")
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String title;

	private String genre;

	private String year;
	private String studio;
	private String synopsis;
	private String imageUrl;
	private String movieUrl;
	private String actors;
	@Nullable
	private String director;
	private String country;
	private String rating;

	private String availability;
	@Nullable
	private double price;
	private boolean isDeleted;
	private float avgStarRating = 0;
	private int noOfPlays = 0;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "movieId")
	private List<Review> reviews;

	public int getNoOfPlays() {
		return noOfPlays;
	}

	public void setNoOfPlays(int noOfPlays) {
		this.noOfPlays = noOfPlays;
	}

	public int getMovieId() {
		return id;
	}

	public void setMovieId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getStudio() {
		return studio;
	}

	public void setStudio(String studio) {
		this.studio = studio;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String image) {
		this.imageUrl = image;
	}

	public String getMovieUrl() {
		return movieUrl;
	}

	public void setMovieUrl(String movie) {
		this.movieUrl = movie;
	}

	public String getActors() {
		return actors;
	}

	public void setActors(String actors) {
		this.actors = actors;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public boolean getIsDeleted() {
		return isDeleted;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public float getAvgStarRating() {
		return avgStarRating;
	}

	public void setAvgStarRating(float avgStarRating) {
		this.avgStarRating = avgStarRating;
	}

}

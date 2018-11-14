package com.backend.netflix.beans;

import java.util.Date;

public class Movie {
	
	
	private String title;


	 
	 private String genre;
	 
	 private Date year;
	 

		private String studio;
	    
	    private String synopsis;
	   
		private String image;
	    
	    private String movie;
	    
	    private String actors;
	    
	    private String director;
	    
	    private String country;
	    
	    private String rating;
	    
	    private String availability;
	    
	    private double price;
	    
	    
	    public int getnoofstar() {
			return noofstar;
		}


		public void setNoOfstars(int noofstars) {
			this.noofstar = noofstar;
		}

		private int noofstar;
	    
	    

	    public Movie(String title, String genre, Date year, String studio, String synopsis, String image, String movie,
			String actors, String director, String country, String rating, String availability, double price) {
		super();
		this.title = title;
		this.genre = genre;
		this.year = year;
		this.studio = studio;
		this.synopsis = synopsis;
		this.image = image;
		this.movie = movie;
		this.actors = actors;
		this.director = director;
		this.country = country;
		this.rating = rating;
		this.availability = availability;
		this.price = price;
	}
	    

		public Movie(String title, String genre, Date year, String studio, String synopsis, String image, String movie,
				String actors, String director, String country, String rating, String availability, double price,
				int noOfstars) {
			super();
			this.title = title;
			this.genre = genre;
			this.year = year;
			this.studio = studio;
			this.synopsis = synopsis;
			this.image = image;
			this.movie = movie;
			this.actors = actors;
			this.director = director;
			this.country = country;
			this.rating = rating;
			this.availability = availability;
			this.price = price;
			this.noofstar = noofstar;
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

		public Date getYear() {
			return year;
		}

		public void setYear(Date year) {
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

		public String getImage() {
			return image;
		}

		public void setImage(String image) {
			this.image = image;
		}

		public String getMovie() {
			return movie;
		}

		public void setMovie(String movie) {
			this.movie = movie;
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
	    

}

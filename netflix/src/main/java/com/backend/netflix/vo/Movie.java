 
package com.backend.netflix.vo;

import com.backend.netflix.vo.User;
import javax.persistence.*;

import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="movies")
public class Movie{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
    private long id;
    @Column(name="title")
    private String title;
    @Column(name="genre")
    private String genre;
    @Column(name="year")
    private String year;
    @Column(name="studio")
    private String studio;
    @Column(name="synopsis")
    private String synopsis;
    @Column(name="image_url")
    private String imageUrl;
    @Column(name="movie_url")
    private String movieUrl;
    @Column(name="actors")
    private String actors;
    @Column(name="director")
    private String director;
    @Column(name="country")
    private String country;
    @Column(name="rating")
    private String rating;

    @Column(name="availability")
    private String availability;
    @Column(name="price")
    @Nullable
    private double price;
    private boolean isDeleted;
    private int noOfStars;
    private Long noOfPlays;
    //@ManyToMany(mappedBy = "movies", targetEntity = User.class)
    @OneToMany(mappedBy="movie", fetch = FetchType.LAZY)
    //@OneToMany
    private List<UserMovie> userMovies;
    
    
    public List<UserMovie> getUsersMovies() {
        return userMovies;
    }

	public void setUsersMovies(List<UserMovie> userMovies) {
		this.userMovies = userMovies;
	}
    
//    public void addActivity(UserActivity activity) {
//    	activites.add(activity);
//        activity.setMovie(this);
//    }

	public Long getNoOfPlays() {
		return noOfPlays;
	}

	public void setNoOfPlays(Long noOfPlays) {
		this.noOfPlays = noOfPlays;
	}
	
    public long getMovieId() {
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

    public String getImage() {
        return imageUrl;
    }

    public void setImage(String image) {
        this.imageUrl = image;
    }

    public String getMovie() {
        return movieUrl;
    }

    public void setMovie(String movie) {
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
    
    public int getNoOfStars() {
		return noOfStars;
	}
    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }
	public void setNoOfStars(int noOfStars) {
		this.noOfStars = noOfStars;
	}




}




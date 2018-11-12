package com.backend.netflix.vo;
import com.backend.netflix.vo.User;
import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="movies")
public class Movie{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="movie_id")
    private long movieId;

    @Column(name="title")
    private String title;

    @Column(name="genre")
    private String genre;
    @Column(name="year")
    private Date year;
    @Column(name="studio")
    private String studio;
    @Column(name="synopsis")
    private String synopsis;
    @Column(name="image")
    private String image;
    @Column(name="movie")
    private String movie;
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
    private double price;

    @ManyToMany(mappedBy="users")
    private Set<User>users=new HashSet<>();

    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }




}

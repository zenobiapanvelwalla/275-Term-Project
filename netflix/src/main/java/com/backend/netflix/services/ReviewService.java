package com.backend.netflix.services;

import com.backend.netflix.beans.HighRatedMovie;
import com.backend.netflix.beans.TopMovie;
import com.backend.netflix.repository.MovieRepository;
import com.backend.netflix.repository.ReviewRepository;
import com.backend.netflix.vo.Movie;
import com.backend.netflix.vo.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    private MovieRepository mRepo;

    public List<Review> getAllReviewsFoMovieId(int movieId) {
        return reviewRepository.findByMovieId(movieId);
    }


    public List<HighRatedMovie> getTopTenMoviesByStarRating() {

        List<BigDecimal> countList = null;
        List<Integer> movieList = null;


        countList = reviewRepository.getTopTenAvgStarMovieRatings();
        movieList = reviewRepository.getTopTenMoviesByStarRating();

        List<HighRatedMovie> highRatedMovies = new ArrayList<HighRatedMovie>();
        for(int i=0; i<movieList.size();i++) {
            Movie movie = mRepo.findById(movieList.get(i)).get();
            HighRatedMovie movie1 = new HighRatedMovie();

            movie1.setMovieId(movie.getMovieId());
            movie1.setTitle(movie.getTitle());
            movie1.setActors(movie.getActors());
            movie1.setAvailability(movie.getAvailability());
            movie1.setCountry(movie.getCountry());
            movie1.setDirector(movie.getDirector());
            movie1.setGenre(movie.getGenre());
            movie1.setImageUrl(movie.getImageUrl());
            movie1.setIsDeleted(movie.getIsDeleted());
            movie1.setNoOfPlays(movie.getNoOfPlays());
            movie1.setPrice(movie.getPrice());
            movie1.setMovieUrl(movie.getMovieUrl());
            movie1.setRating(movie.getRating());
            movie1.setStudio(movie.getStudio());
            movie1.setSynopsis(movie.getSynopsis());
            movie1.setYear(movie.getYear());
            movie1.setAvgStarRating(countList.get(i));

            highRatedMovies.add(movie1);
        }

        return highRatedMovies;
    }

    public Movie addReview(Review review) {
    	reviewRepository.save(review);
        Movie movie = mRepo.findById(review.getMovieId());
        reviewRepository.save(review);
        List<Review> reviews = reviewRepository.findByMovieId(movie.getMovieId());
        if(reviews.size()>0){
            float avgStarRating = reviewRepository.getAvgStarRatingByMovieId(movie.getMovieId()).get();
            movie.setAvgStarRating(avgStarRating);
        }
        else
            movie.setAvgStarRating(review.getStarRating());
       Movie m =  mRepo.save(movie);
        return m;
    }
}


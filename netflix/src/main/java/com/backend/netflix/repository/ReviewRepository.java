package com.backend.netflix.repository;

import com.backend.netflix.vo.Review;
import com.backend.netflix.vo.UserActivity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Integer>  {

    Review findByUserId(int userid);

    List<Review> findByMovieId(int movieId);

    @Transactional
    @Query(value="SELECT AVG(star_rating) FROM reviews where movie_id = ?;",nativeQuery = true)
    Optional<Float> getAvgStarRatingByMovieId(int movieId);

    @Transactional
    @Query(value="select movie_id from reviews where DATEDIFF(now(),review_time_stamp)<=30 GROUP BY movie_id ORDER BY AVG(star_rating) DESC LIMIT 10;",nativeQuery=true)
    List<Integer> getTopTenMoviesByStarRating();

    @Transactional
    @Query(value="select AVG(star_rating) from reviews where DATEDIFF(now(),review_time_stamp)<=30 GROUP BY movie_id ORDER BY AVG(star_rating) DESC LIMIT 10;",nativeQuery=true)
    List<BigDecimal> getTopTenAvgStarMovieRatings();
}

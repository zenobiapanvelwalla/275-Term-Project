package com.backend.netflix.services;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;


//import com.backend.netflix.beans.TopUser;
import com.backend.netflix.repository.MovieRepository;
import com.backend.netflix.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.netflix.vo.UserActivity;
import com.backend.netflix.vo.User;
import com.backend.netflix.vo.Movie;
import com.backend.netflix.beans.TopMovie;
import com.backend.netflix.beans.TopUser;
import com.backend.netflix.repository.UserActivityRepository;

@Service
public class UserActivityService {
	@Autowired
	private UserActivityRepository uaRepo;
	@Autowired
	private UserRepository uRepo;
	@Autowired
	private MovieRepository mRepo;
	
	public List<UserActivity> getUserActivityByUserId(int userId) {
		return uaRepo.findByUserId(userId);
	}

	public List<UserActivity> getUserActivityByUserIdOrderByUpdatedAt(int userId) {
		return uaRepo.findByUserIdOrderByUpdatedAt(userId);
	}
	
	public UserActivity getLatestUserActivityByUserIdAndMovieId(int userId, int movieId) {
		return uaRepo.findLatestByUserIdAndMovieId(userId, movieId);
	}
	
	//create another api for end of movie which will set the checkpoint and isWatched to true
	
	
	public void addUserActivity(int userId, int movieId,long checkpoint) {
		List<UserActivity> ua = uaRepo.findByUserIdAndMovieId(userId, movieId);

		if (ua.size() > 0) {
			UserActivity uactivity = ua.get(0);
			
			//if user activity exists then we need to update the checkpoint. If the user plays and the previous checkpoint value is 
			//> 0 then the new checkpoint is old + new
			//long newCheckPoint = uactivity.getCheckpoint() + checkpoint;
			//uactivity.setCheckpoint(newCheckPoint);
			//uaRepo.save(uactivity);
			
			LocalDateTime now = LocalDateTime.now();
			Duration duration = Duration.between(now, uactivity.getCreatedAt());
			long diff = Math.abs(duration.toHours());
			//what if the user watches the other half of the movie after 24 hours, does that count as another watch??
			if (diff < 24) {
				uactivity.setWatched(false);
				uactivity.setWatching(true);
				uactivity.setCheckpoint(checkpoint);
				uactivity.setUpdatedAt(LocalDateTime.now());
				uaRepo.save(uactivity);
			} else {
				uactivity.setWatched(true);
				uactivity.setWatching(false);
				//uactivity.setUpdatedAt(LocalDateTime.now());  //not touching updated_at
				uaRepo.save(uactivity);
				//if the user resumes playing after 24 hours then his checkpoint should be maintained, even if a new user activity is created.
				utilAddUserActivity(userId, movieId,checkpoint);
			}

		} else {
			utilAddUserActivity(userId, movieId,checkpoint);
		}
	}
	public void utilAddUserActivity(int userId, int movieId, long checkpoint) {
		User optuser = uRepo.findById(userId);
		User user = (User) optuser;
		user.setNoOfPlays(user.getNoOfPlays()+1);
		uRepo.save(user);

		Movie movie = mRepo.findById(movieId);
		movie.setNoOfPlays(movie.getNoOfPlays()+1);
		mRepo.save(movie);

		UserActivity ua = new UserActivity();
		ua.setUserId(userId);
		ua.setMovie(movie);

		ua.setCreatedAt(LocalDateTime.now());
		ua.setUpdatedAt(LocalDateTime.now());
		//ua.setCheckpoint(LocalTime.MIN);
		ua.setCheckpoint(checkpoint);
		ua.setWatching(true);
		ua.setWatched(false);

		uaRepo.save(ua);
	}
	
	//updating user activity if user finished watching the movie
	public void updateUserActivity(int user_id, int movie_id, long checkpoint) {
		UserActivity activity = uaRepo.findLatestByUserIdAndMovieId(user_id, movie_id);
		activity.setCheckpoint(checkpoint);
		activity.setUpdatedAt(LocalDateTime.now());
		activity.setWatched(true);
		activity.setWatching(false);
		uaRepo.save(activity);
		
	}

	public int getNumberOfPlaysForMovie(int movieId, int type) {
		int count = 0;
		if(type==24) {
			count = uaRepo.getNumberOfPlaysForMovieInTwentyFourHours(movieId);
		
		}else if(type==7) {
			count = uaRepo.getNumberOfPlaysForMovieInAWeek(movieId);
		}else {
			count = uaRepo.getNumberOfPlaysForMovieInAMonth(movieId);
		}
		return count;
	}
	
	public List<TopMovie>getTopTenMovies(int type) {
		
		List<BigInteger> countList= null;
		List<Integer> movieList= null ;
		
		if(type==24) {
			
			countList = uaRepo.getTopTenMoviesPlayCountsInTwentyFourHours();
			movieList = uaRepo.getTopTenMoviesInTwentyFourHours();
			
		} else if(type==7) {
			countList = uaRepo.getTopTenMoviesPlayCountsInAWeek();
			movieList = uaRepo.getTopTenMoviesInWeek();
		}else {
			
			countList = uaRepo.getTopTenMoviesPlayCountsInAMonth();
			movieList = uaRepo.getTopTenMoviesInAMonth();
		}
		List<TopMovie> topMovies = new ArrayList<TopMovie>();
		for(int i=0; i<movieList.size();i++) {
			Movie movie = mRepo.findById(movieList.get(i)).get();
			TopMovie movie1 = new TopMovie();
			
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
			movie1.setAvgStarRating(movie.getAvgStarRating());
			movie1.setRating(movie.getRating());
			movie1.setStudio(movie.getStudio());
			movie1.setSynopsis(movie.getSynopsis());
			movie1.setYear(movie.getYear());
			movie1.setPlayCount(countList.get(i));
			
			topMovies.add(movie1);
		}
		
		return topMovies;
	}
	
		public List<TopUser> getTopTenUsers(int type) {
		List<BigInteger> countList= null;
		List<Integer> userList= null ;

		if(type==24) {

			countList = uaRepo.getTopTenUsersPlayCountsInTwentyFourHours();
			userList = uaRepo.getTopTenUsersInTwentyFourHours();

		} else if(type==7) {
			countList = uaRepo.getTopTenUsersPlayCountsInAWeek();
			userList = uaRepo.getTopTenUsersInWeek();
		}else {

			countList = uaRepo.getTopTenUsersPlayCountsInAMonth();
			userList = uaRepo.getTopTenUsersInAMonth();
		}
		List<TopUser> topUsers = new ArrayList<TopUser>();
		for(int i=0; i<userList.size();i++) {
			User user = uRepo.findById(userList.get(i)).get();
			TopUser user1 = new TopUser();

			user1.setId(user.getId());
			user1.setDisplayName(user.getDisplayName());
			user1.setEmail(user.getEmail());
			user1.setPlayCount(countList.get(i));

			topUsers.add(user1);
		}

		return topUsers;
	}

	public List<UserActivity> getUserActivityList(int userId, int movieId){
		return uaRepo.findByUserIdAndMovieId(userId,movieId);
	}

	public List<Movie> getMoviesInProgress(int userId) {
		return uaRepo.getMoviesInProgress(userId);
	}

}

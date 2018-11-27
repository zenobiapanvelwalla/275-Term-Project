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

	public void addUserActivity(int userId, int movieId) {
		List<UserActivity> ua = uaRepo.findByUserIdAndMovieId(userId, movieId);

		if (ua.size() > 0) {
			UserActivity uactivity = ua.get(0);
			LocalDateTime now = LocalDateTime.now();
			Duration duration = Duration.between(now, uactivity.getCreatedAt());
			long diff = Math.abs(duration.toHours());
			if (diff < 24) {
				uactivity.setWatched(false);
				uactivity.setWatching(true);
				//java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());
				uactivity.setUpdatedAt(LocalDateTime.now());
				uaRepo.save(uactivity);
			} else {
				utilAddUserActivity(userId, movieId);
			}

		} else {
			utilAddUserActivity(userId, movieId);
		}
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
			movie1.setNoOfStars(movie.getNoOfStars());
			movie1.setRating(movie.getRating());
			movie1.setStudio(movie.getStudio());
			movie1.setSynopsis(movie.getSynopsis());
			movie1.setYear(movie.getYear());
			movie1.setPlayCount(countList.get(i));
			
			topMovies.add(movie1);
		}
		
		return topMovies;
	}
	
	public void utilAddUserActivity(int userId, int movieId) {
		Optional<User> optuser = uRepo.findById(userId);
		User user = (User) optuser.get();
		user.setNoOfPlays(user.getNoOfPlays()+1);
		uRepo.save(user);

		Movie movie = mRepo.findById(movieId);
		movie.setNoOfPlays(movie.getNoOfPlays()+1);
		mRepo.save(movie);

		UserActivity ua = new UserActivity();
		ua.setUserId(userId);
		ua.setMovie(movie);
		//java.sql.Date sqlDate = new java.sql.Date(new LocalDateTime.now());
		ua.setCreatedAt(LocalDateTime.now());
		ua.setUpdatedAt(LocalDateTime.now());
		//SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		/*Calendar now = Calendar.getInstance();
		now.set(Calendar.HOUR,0);
		now.set(Calendar.MINUTE,0);
		now.set(Calendar.SECOND,0);
*/
		ua.setCheckpoint(LocalTime.MIN);
		ua.setWatching(true);
		ua.setWatched(false);

		uaRepo.save(ua);
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
}

package com.backend.netflix.services;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.Temporal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;


import com.backend.netflix.repository.MovieRepository;
import com.backend.netflix.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.netflix.vo.UserActivity;
import  com.backend.netflix.vo.User;
import com.backend.netflix.vo.Movie;
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

	public void addUserActivity(int userId, int movieId) {
		List<UserActivity> ua = uaRepo.findByUserIdAndMovieId(userId, movieId);

		if (ua.size() > 0) {
			UserActivity uactivity = ua.get(0);
			LocalDateTime now = LocalDateTime.now();
			Duration duration = Duration.between(now, uactivity.getTimeStamp());
			long diff = Math.abs(duration.toHours());
			if (diff < 24) {
				uactivity.setWatched(false);
				uactivity.setWatching(true);
				//java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());
				uactivity.setTimeStamp(LocalDateTime.now());
			} else {
				utilAddUserActivity(userId, movieId);
			}

		} else {
			utilAddUserActivity(userId, movieId);
		}
	}

	public void utilAddUserActivity(int userId, int movieId) {
		Optional<User> optuser = uRepo.findById(userId);
		User user = (User) optuser.get();
		user.setNoOfPlays(user.getNoOfPlays()+1);

		//Optional<Movie> optmovie = mRepo.findById(movieId);
		Movie movie = mRepo.findById(movieId);
		movie.setNoOfPlays(movie.getNoOfPlays()+1);
		UserActivity ua = new UserActivity();
		ua.setUserId(userId);
		ua.setMovie(movie);
		//java.sql.Date sqlDate = new java.sql.Date(new LocalDateTime.now());
		ua.setTimeStamp(LocalDateTime.now());

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
}

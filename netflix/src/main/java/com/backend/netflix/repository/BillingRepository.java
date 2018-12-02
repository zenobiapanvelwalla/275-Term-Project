package com.backend.netflix.repository;

import java.util.List;
import java.util.Optional;

import com.backend.netflix.vo.PaymentType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backend.netflix.vo.Billing;



@Repository
public interface BillingRepository extends CrudRepository<Billing, Integer> {
	
	
	public static final String FIND_USERS = "SELECT  userId FROM Billing where pstatus=?";

	@Query(value = FIND_USERS, nativeQuery = true)
	public List<Integer> findBilling(PaymentType paymentType);

	  Billing findByUserId(int userId);
	  
//	  @Transactional
//	    @Query(value="SELECT AVG(star_rating) FROM reviews where movie_id = ?;",nativeQuery = true)
//	    Optional<Float> getAvgStarRatingByMovieId(int movieId);
//	  


//	@Transactional
//	@Query(value="SELECT COUNT(DISTINCT user_id) FROM billing WHERE payment_type = 'payPerView';",nativeQuery=true)
//	int getCountOfUniquePayPerViewUsers();


}

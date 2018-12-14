package com.backend.netflix.repository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import com.backend.netflix.vo.PaymentType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backend.netflix.beans.MonthlyDetails;
import com.backend.netflix.vo.Billing;

@Repository
public interface BillingRepository extends CrudRepository<Billing, Integer> {

	Billing findByUserId(int userId);

	@Query(value="SELECT movie_id FROM billing WHERE user_id=? AND p_type IN('PayPerViewOnly','Paid')",nativeQuery = true)
	public List<Integer> getListOfMoviesUserHasPaidFor(int userId);

	@Transactional
	@Query(value="SELECT SUM(money_paid) FROM billing WHERE p_type = ? AND TIMESTAMPDIFF(MONTH,billdate, now())<12 GROUP BY month(billdate),year(billdate) ORDER BY year(billdate) DESC, month(billdate) DESC;",nativeQuery=true)
	List<BigDecimal>  getIncomeListBasedOnPaymentType(String pType);

	@Transactional
	@Query(value="SELECT month(billdate) FROM billing WHERE p_type = ? AND TIMESTAMPDIFF(MONTH,billdate, now())<12 GROUP BY month(billdate),year(billdate) ORDER BY year(billdate) DESC, month(billdate) DESC;",nativeQuery=true)
	List<Integer> getMonthsListBasedOnPaymentType(String pType);

	@Transactional
	@Query(value = "SELECT month(billdate)FROM billing WHERE TIMESTAMPDIFF(MONTH,billdate, now())<12 GROUP BY month(billdate),year(billdate) ORDER BY year(billdate) DESC, month(billdate) DESC;",nativeQuery = true)
	List<Integer> getMonthsListForTotalIncome();

	@Transactional
	@Query(value = "SELECT SUM(money_paid) FROM billing WHERE TIMESTAMPDIFF(MONTH,billdate, now())<12 GROUP BY month(billdate),year(billdate) ORDER BY year(billdate) DESC, month(billdate) DESC;",nativeQuery = true)
	List<BigDecimal> getIncomeListForTotalIncome();

	@Transactional
	@Query(value="select count(DISTINCT user_id) from billing where p_type=?",nativeQuery = true)
	Integer getUsersBasedOnSubsription(String pType);
	
	
	
	//Question --unique pay-per-view users (those who have played at least one Pay-Per-View movie
	@Query(value="SELECT MONTH(billdate) FROM billing as b WHERE p_type=\"PayPerViewOnly\" and user_id in (select user_id FROM users_activities u WHERE checkpoint>0 AND u.movie_id=b.movie_id) AND TIMESTAMPDIFF(MONTH,billdate, now())<12 GROUP BY month(billdate),year(billdate) ORDER BY year(billdate) DESC, month(billdate) DESC;",nativeQuery=true)
	List<Integer> getCountOfUniquePayPerViewUsers_Months();
	
	//Question --unique pay-per-view users (those who have played at least one Pay-Per-View movie
	@Query(value="SELECT COUNT(DISTINCT user_id) FROM billing as b WHERE p_type=\"PayPerViewOnly\" and user_id in (select user_id FROM users_activities u WHERE checkpoint>0 AND u.movie_id=b.movie_id) AND TIMESTAMPDIFF(MONTH,billdate, now())<12 GROUP BY month(billdate),year(billdate) ORDER BY year(billdate) DESC, month(billdate) DESC;",nativeQuery=true)
	List<BigInteger> getCountOfUniquePayPerViewUsers_UserCount();

}

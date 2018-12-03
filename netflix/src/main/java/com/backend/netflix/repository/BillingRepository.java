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

	public static final String FIND_USERS = "SELECT userId FROM Billing where pType =?";

	//@Query(value = FIND_USERS, nativeQuery = true)
	//public List<Integer> getUserIdListByPaymentType(String pType);

	Billing findByUserId(int userId);

	@Query(value="SELECT movie_id FROM billing WHERE user_id=? AND p_type IN('PayPerViewOnly','Paid')",nativeQuery = true)
	public List<Integer> getListOfMoviesUserHasPaidFor(int userId);

	@Transactional
	@Query(value="SELECT SUM(money_paid) FROM billing WHERE p_type = ? AND TIMESTAMPDIFF(MONTH,billdate, now())<=12 GROUP BY month(billdate),year(billdate) ORDER BY year(billdate) DESC, month(billdate) DESC;",nativeQuery=true)
	List<BigDecimal>  getIncomeListBasedOnPaymentType(String pType);

	@Transactional
	@Query(value="SELECT month(billdate) FROM billing WHERE p_type = ? AND TIMESTAMPDIFF(MONTH,billdate, now())<=12 GROUP BY month(billdate),year(billdate) ORDER BY year(billdate) DESC, month(billdate) DESC;",nativeQuery=true)
	List<Integer> getMonthsListBasedOnPaymentType(String pType);

	@Transactional
	@Query(value = "SELECT month(billdate)FROM billing WHERE TIMESTAMPDIFF(MONTH,billdate, now())<=12 GROUP BY month(billdate),year(billdate) ORDER BY year(billdate) DESC, month(billdate) DESC;",nativeQuery = true)
	List<Integer> getMonthsListForTotalIncome();

	@Transactional
	@Query(value = "SELECT SUM(money_paid) FROM billing WHERE TIMESTAMPDIFF(MONTH,billdate, now())<=12 GROUP BY month(billdate),year(billdate) ORDER BY year(billdate) DESC, month(billdate) DESC;",nativeQuery = true)
	List<BigDecimal> getIncomeListForTotalIncome();

	@Transactional
	@Query(value="select count(DICTINCT user_id) as count from billing where TIMESTAMPDIFF(MONTH,billdate, now())<=12 GROUP BY month(billdate),year(billdate) DESC;",nativeQuery = true)
	BigInteger getCountOfUniqueSubscriptionUsers();
	
	@Transactional
	@Query(value="select count(DISTINCT user_id) from billing where p_type=?",nativeQuery = true)
	Integer getUsersBasedOnSubsription(String pType);
	
//	@Transactional
//	@Query(value="SELECT month(billdate),user_id FROM billing WHERE\r\n" + 
//			" TIMESTAMPDIFF(MONTH,billdate, now())<=12 GROUP BY month(billdate),year(billdate)\r\n" + 
//			" ORDER BY year(billdate) DESC, month(billdate) DESC",nativeQuery = true)
//	List<MonthlyDetails> getUserRegisteredMonthly();
	
	@Transactional
	@Query(value="SELECT user_id FROM billing WHERE\r\n" + 
			" p_type=? AND TIMESTAMPDIFF(MONTH,billdate, now())<=12 GROUP BY month(billdate),year(billdate)\r\n" + 
			" ORDER BY year(billdate) DESC, month(billdate) DESC",nativeQuery = true)
	List<Integer> getUserMontlyUserbasedOnSubscriptionMonthly(String pType);
	
	
	@Transactional
	@Query(value="SELECT month(billdate) FROM billing WHERE\r\n" + 
			" p_type=? AND TIMESTAMPDIFF(MONTH,billdate, now())<=12 GROUP BY month(billdate),year(billdate)\r\n" + 
			" ORDER BY year(billdate) DESC, month(billdate) DESC",nativeQuery = true)
	List<Integer> getMonthMontlyUserbasedOnSubscriptionMonthly(String pType);
	
	
//	protected default void setUp() throws Exception {
//	    entityManagerFactory = 
//	}
	
//	public default Map<Integer,Integer> getUserRegisteredMonthly() {
//		   // create empty map to store results in. If we don't find results, an empty hashmap will be returned
//		   Map<Integer,Integer> results = new HashMap<>();
//		   
//
//		   EntityManager em = Persistence.createEntityManagerFactory( "org.hibernate.tutorial.jpa" ).createEntityManager();
//
//		   // Construct and run query
//		   String jpaQuery = "SELECT month(billdate),user_id FROM billing WHERE\\r\\n\" + \r\n" + 
//		   		"			\" TIMESTAMPDIFF(MONTH,billdate, now())<=12 GROUP BY month(billdate),year(billdate)\\r\\n\" + \r\n" + 
//		   		"			\" ORDER BY year(billdate) DESC, month(billdate) DESC";
//		   List<Object[]> resultList = em.createQuery(jpaQuery).getResultList();
//
//		   // Place results in map
//		   for (Object[] borderTypes: resultList) {
//		      results.put((Integer)borderTypes[0], (Integer)borderTypes[1]);
//		   }
//
//		   return results;
//		}
	
	
	
	

}

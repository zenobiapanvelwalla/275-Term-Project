package com.backend.netflix.repository;

import java.math.BigDecimal;
import java.math.BigInteger;
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

	public static final String FIND_USERS = "SELECT userId FROM Billing where pType =?";

	//@Query(value = FIND_USERS, nativeQuery = true)
	//public List<Integer> getUserIdListByPaymentType(String pType);

	Billing findByUserId(int userId);

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

}

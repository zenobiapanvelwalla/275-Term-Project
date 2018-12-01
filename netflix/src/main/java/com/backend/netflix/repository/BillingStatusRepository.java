package com.backend.netflix.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.backend.netflix.vo.Billing;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface BillingStatusRepository extends CrudRepository<Billing, Integer> {
	

	  Billing findByUserId(int userId);

	@Transactional
	@Query(value="SELECT COUNT(DISTINCT user_id) FROM billing WHERE payment_type = 'payPerView';",nativeQuery=true)
	int getCountOfUniquePayPerViewUsers();

}

package com.backend.netflix.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.backend.netflix.vo.Billing;


@Repository
public interface BillingStatusRepository extends CrudRepository<Billing, Integer> {
	

	  Billing findByUserId(int userId);

}

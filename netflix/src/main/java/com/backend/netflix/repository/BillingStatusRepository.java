package com.backend.netflix.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.backend.netflix.vo.BillingStatus;


@Repository
public interface BillingStatusRepository extends CrudRepository<BillingStatus, Integer> {
	

	  BillingStatus findByUserId(int userId);

}

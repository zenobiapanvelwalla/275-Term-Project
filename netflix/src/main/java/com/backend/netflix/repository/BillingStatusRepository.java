package com.backend.netflix.repository;
import org.springframework.data.repository.CrudRepository;
import com.backend.netflix.vo.BillingStatus;


public interface BillingStatusRepository extends CrudRepository<BillingStatus, Integer> {
	
	  BillingStatus findByUserId(int userId);
}

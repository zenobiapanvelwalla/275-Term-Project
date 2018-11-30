package com.backend.netflix.repository;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;
import com.backend.netflix.vo.BillingStatus;
import com.backend.netflix.vo.UserSubscription;

@Repository
public interface BillingStatusRepository extends CrudRepository<BillingStatus, Integer> {
	
	  BillingStatus findByUserId(int userid);
}

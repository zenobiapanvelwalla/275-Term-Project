package com.backend.netflix.repository;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.backend.netflix.beans.BillingStatus;
import com.backend.netflix.beans.UserSubscription;

public interface BillingStatusRepository extends CrudRepository<BillingStatus, Integer> {
	
	Set<UserSubscription> findByUserid(String userId);


}

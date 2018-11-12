package com.backend.netflix.repository;



import org.springframework.data.repository.CrudRepository;
import java.util.Set;
import com.backend.netflix.beans.BillingStatus;
import com.backend.netflix.beans.UserSubscription;

public interface BillingStatusRepository extends CrudRepository<BillingStatus, Long> {
	
	Set<UserSubscription> findByUserid(String userId);
}

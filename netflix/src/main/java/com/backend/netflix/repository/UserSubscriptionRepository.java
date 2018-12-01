package com.backend.netflix.repository;

import org.springframework.data.repository.CrudRepository;
import com.backend.netflix.vo.UserSubscription;


import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.backend.netflix.vo.User;

@Repository
public interface UserSubscriptionRepository extends CrudRepository<UserSubscription, Integer> {

	UserSubscription findByUserId(int  Uid);

	@Transactional
	@Query(value="SELECT COUNT(DISTINCT user_id) FROM user_subscriptions WHERE end_date >= NOW();",nativeQuery=true)
	int getCountOfUniqueSubscriptionUsers();


}

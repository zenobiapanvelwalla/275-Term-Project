package com.backend.netflix.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.backend.netflix.beans.UserSubscription;
import com.backend.netflix.vo.User;
import com.backend.netflix.vo.UserRoles;

public interface UserSubscriptionRepository extends CrudRepository<UserSubscription, Integer> {

	Set<UserSubscription> findByUid(String Uid);

}

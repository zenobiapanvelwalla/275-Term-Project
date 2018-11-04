package com.backend.netflix.interfaces;

import org.springframework.data.repository.CrudRepository;
import com.backend.netflix.vo.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}

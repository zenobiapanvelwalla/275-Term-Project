package com.backend.netflix.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.backend.netflix.beans.Role;
import com.backend.netflix.beans.Roles;
import com.backend.netflix.vo.User;

public interface RoleRepository extends CrudRepository<Role, Integer> {

	Set<Role> findById(int id);

}

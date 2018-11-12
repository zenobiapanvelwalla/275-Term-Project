package com.backend.netflix.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_role")
public class UserRoles {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int id;
	public int getId() {
		return id;
	}
	public int getRole_id() {
		return role_id;
	}
	public int getUser_id() {
		return user_id;
	}
	int role_id;
	int user_id;
	
	public void setId(int id) {
		this.id = id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	
}

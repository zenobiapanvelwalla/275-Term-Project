package com.backend.netflix.beans;

public class Role {

	private int id;
	
	
	private Roles role;


	public int getId() {
		return id;
	}


	public Role(int id, Roles role) {
		super();
		this.id = id;
		this.role = role;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Roles getRole() {
		return role;
	}


	public void setRole(Roles role) {
		this.role = role;
	}
	
}

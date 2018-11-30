package com.backend.netflix.vo;



import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;







@Entity
@Table(name="users")
public class User {
	
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int id;
	private String email;
	private String password;
	private String displayName;
	private boolean verified;
	private String verificationCode;
	private String role;
	
//	@OneToOne(fetch = FetchType.EAGER,
//            cascade =  CascadeType.ALL,
//            mappedBy = "user")
//	private UserSubscription subscription;
	
//	@OneToMany(fetch=FetchType.EAGER, mappedBy = "userId")
//	private List<BillingStatus> bills;
	
	public User() {}
	public User(int id, String email, String password, String displayName, boolean verified, String verificationCode,
			String role) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.displayName = displayName;
		this.verified = verified;
		this.verificationCode = verificationCode;
		this.role = role;
	}
	
	private int noOfPlays =0;

    public int getNoOfPlays() {
        return noOfPlays;
    }

    public void setNoOfPlays(int noOfPlays) {
        this.noOfPlays = noOfPlays;
    }

    

	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public boolean isVerified() {
		return verified;
	}
	public void setVerified(boolean verified) {
		this.verified = verified;
	}
	public String getVerificationCode() {
		return verificationCode;
	}
	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}
	
   public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
//	public UserSubscription getSubscription() {
//		return subscription;
//	}
//
//	public void setSubscription(UserSubscription subscription) {
//		this.subscription = subscription;
//	}
	
}

package com.backend.netflix.vo;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;





@Entity
@Table(name="users")
public class User {
	
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int id;

	@Column(name="email")
	private String email;
	private String password;
	private String displayName;
	private boolean verified;
	private String verificationCode;
	private String role;

	
	
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
	
}

package com.ust.model;

public class Login {
	
	//Initializing variables
	private String userId;
	private String username;
	private String userPassword;
	
	//getters and setters
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	//constructors
	public Login() {
		super();
	}
	public Login(String userId, String username, String userPassword) {
		super();
		this.userId = userId;
		this.username = username;
		this.userPassword = userPassword;
	}
	
	@Override
	public String toString() {
		return "Login [userId=" + userId + ", username=" + username
				+ ", userPassword=" + userPassword + "]";
	}
	
	
	
	

}

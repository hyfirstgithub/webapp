package com.netease.course.pojo;


public class User {
	private String username;
	private int usertype;
	
	
	
	
	public User(String username,int userType){
		this.username = username;
		this.usertype = userType;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getUsertype() {
		return usertype;
	}

	public void setUsertype(int usertype) {
		this.usertype = usertype;
	}

	
	
	
	
}

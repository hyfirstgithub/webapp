package com.netease.course.pojo;

public class Person {  
	  
    private String userName;  
    private String password;  
    private int id;
    private String nickName;
    private int userType;
  
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public String getUsername() {  
        return userName;  
    }  
  
    public void setUsername(String username) {  
        this.userName = username;  
    }  
  
    public String getPassword() {  
        return password;  
    }  
  
    public void setPassword(String password) {  
        this.password = password;  
    }  
  
}  

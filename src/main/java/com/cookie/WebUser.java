package com.cookie;

import java.io.Serializable;

public class WebUser implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long userId; 	 //用户ID
	private String userName; //用户名
	private int loginStatus; //登录状态
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getLoginStatus() {
		return loginStatus;
	}
	public void setLoginStatus(int loginStatus) {
		this.loginStatus = loginStatus;
	}
	
}

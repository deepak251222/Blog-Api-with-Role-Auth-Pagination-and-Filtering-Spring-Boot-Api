package com.spring.Entity;

public class UserLogin {
	
	public String loginame;
	public String loginpwd;
	public UserLogin() {
		super();
			}
	public UserLogin(String loginame, String loginpwd) {
		super();
		this.loginame = loginame;
		this.loginpwd = loginpwd;
	}
	public String getLoginame() {
		return loginame;
	}
	public void setLoginame(String loginame) {
		this.loginame = loginame;
	}
	public String getLoginpwd() {
		return loginpwd;
	}
	public void setLoginpwd(String loginpwd) {
		this.loginpwd = loginpwd;
	}
	
	

}

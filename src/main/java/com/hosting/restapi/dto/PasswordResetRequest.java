package com.hosting.restapi.dto;

public class PasswordResetRequest {
	
	String token;
	String password;
	
	public final String getToken() {
		return token;
	}
	public final void setToken(String token) {
		this.token = token;
	}
	public final String getPassword() {
		return password;
	}
	public final void setPassword(String password) {
		this.password = password;
	}
	
}

package com.hosting.restapi.dto;

public class JwtAuthenticationResponse {

	String accessToken;
	String refreshToken;
	

	public JwtAuthenticationResponse() {
		super();
	}

	public JwtAuthenticationResponse(String accessToken, String refreshToken) {
		super();
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}

	public String getAccessToken() {
		return accessToken;
	}
	
	public String getRefreshToken() {
		return refreshToken;
	}
	

	public void setAccessToken(String token) {
		this.accessToken = token;
	}
	
	public void setRefresToken(String token) {
		this.refreshToken = token;
	}
	
}

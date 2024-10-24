package com.hosting.restapi.dto;

public class RefreshTokenRequest {

	String refreshToken;

	public RefreshTokenRequest() {
		super();
	}

	public RefreshTokenRequest(String refreshToken) {
		super();
		this.refreshToken = refreshToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
}

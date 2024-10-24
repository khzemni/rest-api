package com.hosting.restapi.service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hosting.restapi.entity.RefreshToken;
import com.hosting.restapi.repository.RefreshTokenRepository;
import com.hosting.restapi.repository.UserRepository;

@Service
public class RefreshTokenService {
	
	@Value("${refresh.token.expirationms}")
	private Long refreshTokenExpirationMs;
	
	private final RefreshTokenRepository refreshTokenRepository;
	private final UserRepository userRepository;
	
	public RefreshTokenService(RefreshTokenRepository refreshTokenRepository, UserRepository userRepository) {
		this.refreshTokenRepository = refreshTokenRepository;
		this.userRepository = userRepository;
	}
	
	public RefreshToken createRefreshToken(String username) {
		var user = this.userRepository.findByEmail(username).get();
		var token = UUID.randomUUID().toString();
		var expiryDate = Instant.now().plusMillis(refreshTokenExpirationMs);
		
		Optional<RefreshToken> existingExpiredToken = this.refreshTokenRepository.findByUserId(user.getId());
		if(!existingExpiredToken.isEmpty()) {
			this.refreshTokenRepository.delete(existingExpiredToken.get());
		}
		
		RefreshToken refreshtoken = new RefreshToken();			
		refreshtoken.setUser(user);
		refreshtoken.setToken(token);
		refreshtoken.setExpiryDate(expiryDate);
		return refreshTokenRepository.save(refreshtoken);
	}
	
	public Optional<RefreshToken> findByToken(String token) {
		return this.refreshTokenRepository.findByToken(token);
	}
	
	public RefreshToken verifyExpiration(RefreshToken token) {
		if(token.getExpiryDate().compareTo(Instant.now())<0) {
			this.refreshTokenRepository.delete(token);
			throw new RuntimeException(token.getToken() + " Refresh token was expired");
		}
		return token;
	}

}

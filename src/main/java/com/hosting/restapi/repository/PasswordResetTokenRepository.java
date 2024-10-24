package com.hosting.restapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hosting.restapi.entity.PasswordResetToken;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
	
	public Optional<PasswordResetToken> findByUserId(Long userId);
	public Optional<PasswordResetToken> findByToken(String token);

}

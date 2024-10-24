package com.hosting.restapi.service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hosting.restapi.dto.PasswordResetRequest;
import com.hosting.restapi.entity.PasswordResetToken;
import com.hosting.restapi.entity.RefreshToken;
import com.hosting.restapi.repository.PasswordResetTokenRepository;
import com.hosting.restapi.repository.UserRepository;
import com.hosting.restapi.entity.User;

@Service
public class PasswordResetService {
	
	@Value("${password-reset.token.expirationms}")
	private Long expirationMs;

	@Autowired
	private final PasswordResetTokenRepository passwordResetTokenRepository;
	@Autowired 
	private final UserRepository userRepository;
	@Autowired 
	private final JavaMailSender mailSender;
	@Autowired
	private final PasswordEncoder passwordEncoder;
	
	public PasswordResetService(
			PasswordResetTokenRepository passwordResetRepository,
			UserRepository userRepository,
			JavaMailSender mailSender, 
			PasswordEncoder passwordEncoder
			) {
		this.passwordResetTokenRepository = passwordResetRepository;
		this.userRepository = userRepository;
		this.mailSender = mailSender;
		this.passwordEncoder = passwordEncoder;
	}
	
	public PasswordResetToken createResetToken(String email) {
		var user = this.userRepository.findByEmail(email).get();
		var token = UUID.randomUUID().toString();
		var expiryDate = Instant.now().plusMillis(expirationMs);
		
		Optional<PasswordResetToken> existingExpiredToken = this.passwordResetTokenRepository.findByUserId(user.getId());
		if(!existingExpiredToken.isEmpty()) {
			this.passwordResetTokenRepository.delete(existingExpiredToken.get());
		}
		
		PasswordResetToken passwordResetToken = new PasswordResetToken();			
		passwordResetToken.setUser(user);
		passwordResetToken.setToken(token);
		passwordResetToken.setExpiryDate(expiryDate);
		return passwordResetTokenRepository.save(passwordResetToken);
	}
	
	public boolean resetPassword(String email) {
		if(this.userRepository.findByEmail(email).isEmpty()) {
			return false;
		}
		PasswordResetToken resetToken = this.createResetToken(email);
		SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);
        msg.setSubject("Password reset");
        msg.setText("your password reset link is the following :\nhttp://localhost:4200/reset-password/"+resetToken.getToken()+" \nthis link expires in 10 minutes");   
        mailSender.send(msg);
        return true;
	}
	
	public boolean updateForgottenPassword(PasswordResetRequest passwordResetRequest) {
		PasswordResetToken resetToken = this.passwordResetTokenRepository.findByToken(passwordResetRequest.getToken()).get();
		if(!verifyExpiration(resetToken))
			return false;
		User user = resetToken.getUser();
		user.setPassword(passwordEncoder.encode(passwordResetRequest.getPassword()));
		userRepository.save(user);
		this.passwordResetTokenRepository.delete(resetToken);
		return true;
	}
	
	public boolean verifyExpiration(PasswordResetToken token) {
		if(token.getExpiryDate().compareTo(Instant.now())<0) {
			this.passwordResetTokenRepository.delete(token);
			return false;
		}
		return true;
	}
	
}

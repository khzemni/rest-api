package com.hosting.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hosting.restapi.dto.JwtAuthenticationResponse;
import com.hosting.restapi.dto.PasswordResetRequest;
import com.hosting.restapi.dto.RefreshTokenRequest;
import com.hosting.restapi.dto.SignInRequest;
import com.hosting.restapi.dto.SignUpRequest;
import com.hosting.restapi.dto.UserDTO;
import com.hosting.restapi.entity.User;
import com.hosting.restapi.service.AuthenticationService;
import com.hosting.restapi.service.PasswordResetService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*")
public class AutheticationController {
	@Autowired 
	private final AuthenticationService authenticationService;
	@Autowired 
	private final PasswordResetService passwordResetService;
	
	public AutheticationController(AuthenticationService authenticationService, PasswordResetService passwordResetService) {
		this.authenticationService = authenticationService;
		this.passwordResetService = passwordResetService;
	}

    @PostMapping("/signup")
    public JwtAuthenticationResponse signup(@RequestBody SignUpRequest request) {
        return authenticationService.register(request);
    }

    @PostMapping("/signin")
    public JwtAuthenticationResponse signin(@RequestBody SignInRequest request) {
        return authenticationService.authenticate(request);
    }
    
    @PostMapping("/facebook-signin")
    public JwtAuthenticationResponse facebookSignIn(@RequestBody SignInRequest request) {
    	return authenticationService.authenticate(request);
    }
    
    
    @PostMapping("/google-signin")
    public String googleSignIn(HttpServletRequest request) {
//    	return authenticationService.authenticate(request);
    	return request.getQueryString();
    }
    
    @PostMapping("/refresh-token")
    public JwtAuthenticationResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
    	return authenticationService.refreshToken(refreshTokenRequest);
    }
    
    @PostMapping("/logout")
    public void logout() {
        authenticationService.logout();
    }
    
    @GetMapping("/user-details")
    public UserDTO getUserDetails(HttpServletRequest request) {
    	String bearerString = request.getHeader("Authorization");
    	if(bearerString==null) {
    		return null;
    	}
    	return new UserDTO((User)this.authenticationService.getUserDetails(bearerString)) ;
    }
    
    @PostMapping("/reset-password-request")
	public ResponseEntity<String> resetPasswordRequest(@RequestParam String email) {

		if(this.passwordResetService.resetPassword(email))
			return ResponseEntity.ok("a password reset link is sent to your address");
		
		return ResponseEntity.ok("address not valid");
	}
	
	@PostMapping("/reset-password")
	public ResponseEntity<String> resetPassword(@RequestBody PasswordResetRequest resetToken) {
		if(this.passwordResetService.updateForgottenPassword(resetToken))
			return ResponseEntity.ok("Password reset successfully");
		return ResponseEntity.ok("reset failed");
	}
    
}

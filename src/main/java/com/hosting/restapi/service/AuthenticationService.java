package com.hosting.restapi.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hosting.restapi.dto.JwtAuthenticationResponse;
import com.hosting.restapi.dto.RefreshTokenRequest;
import com.hosting.restapi.dto.SignInRequest;
import com.hosting.restapi.dto.SignUpRequest;
import com.hosting.restapi.entity.RefreshToken;
import com.hosting.restapi.entity.Role;
import com.hosting.restapi.entity.User;
import com.hosting.restapi.repository.UserRepository;

@Service
public class AuthenticationService {

  private final UserRepository userRepository;
  private final UserService userService;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  private final RefreshTokenService refreshTokenService;
  
  public AuthenticationService(
		  UserRepository userRepository,
		  UserService userService,
		  PasswordEncoder passwordEncoder,
		  JwtService jwtService,
		  AuthenticationManager authenticationManager,
		  RefreshTokenService refreshTokenService
		  ) {
	  
	  this.userRepository = userRepository;
	  this.userService = userService;
	  this.passwordEncoder = passwordEncoder;
	  this.jwtService = jwtService;
	  this.authenticationManager = authenticationManager;
	  this.refreshTokenService = refreshTokenService;
	  
  }

  public JwtAuthenticationResponse register(SignUpRequest request) {
      var user = new User();           
      user.setFirstName(request.getFirstName());
      user.setLastName(request.getLastName());
      user.setEmail(request.getEmail());
      user.setPassword(passwordEncoder.encode(request.getPassword()));
      user.setRole(Role.ROLE_USER);

      user = userService.saveUser(user);
      var jwt = jwtService.generateToken(user);
      var refresh = refreshTokenService.createRefreshToken(request.getEmail());
      return new JwtAuthenticationResponse(jwt, refresh.getToken());
  }


  public JwtAuthenticationResponse authenticate(SignInRequest request) {
	  
      authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
      
      var user = userRepository.findByEmail(request.getEmail())
              .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
      var refresh = refreshTokenService.createRefreshToken(request.getEmail());
      var jwt = jwtService.generateToken(user);
      return new JwtAuthenticationResponse(jwt, refresh.getToken());
  }
  
  
  public JwtAuthenticationResponse refreshToken(RefreshTokenRequest request) {
      return refreshTokenService.findByToken(request.getRefreshToken())
      .map(refreshTokenService::verifyExpiration)
      .map(RefreshToken::getUser)
      .map(user -> {
    	  var jwt = jwtService.generateToken(user);
    	  return new JwtAuthenticationResponse(jwt, request.getRefreshToken());
      }).orElseThrow(()-> new RuntimeException("Token is not valid"));
  }
  
  public void logout() {
	  
  }
  
  public User getUserDetails(String bearer) {
	  var jwt = bearer.substring(7);
	  String userEmail = jwtService.extractUserName(jwt);
	  if(StringUtils.isNotEmpty(userEmail)) {
			User userDetails = (User) userService.userDetailsService().loadUserByUsername(userEmail);
			if(jwtService.isTokenValid(jwt, userDetails)) {
				return userDetails;
			}
	  }
	  return null;
  }
  
  
}
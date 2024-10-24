package com.hosting.restapi.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.hosting.restapi.filters.JwtAuthenticationFilter;
import com.hosting.restapi.service.UserService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {
	
	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	private final UserService userService;
	private final PasswordEncoder passwordEncoder;
	
	public SecurityConfiguration(
			JwtAuthenticationFilter jwtAuthenticationFilter,
			UserService userService,
			PasswordEncoder passwordEncoder
			) {
		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userService.userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder);
		return authProvider;
	}
	
	@Bean 
	AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	
	@Bean 
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.csrf(csrf -> csrf.disable())
			.sessionManagement(session -> session
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
					)
			.authorizeHttpRequests(authorize -> authorize
					.requestMatchers(
							HttpMethod.POST, 
							"/api/v1/auth/signup",
							"/api/v1/auth/signin",
							"/api/v1/auth/refresh-token",
							"/api/v1/auth/reset-password-request",
							"/api/v1/auth/reset-password",
							"/api/v1/stripe/payment"
//							"/api/v1/fimages"
							).permitAll()
					.requestMatchers(
							HttpMethod.GET, 
							"/api/v1/user-details",
							"/api/v1/offers",
							"/api/v1/offers/**",
							"/api/v1/users/**",
							"api/v1/offer-reservation"
							).permitAll()
					.anyRequest().authenticated()
					)
			.authenticationProvider(authenticationProvider())
			.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
			
		return http.build();
	}
	
}

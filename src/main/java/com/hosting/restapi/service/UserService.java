package com.hosting.restapi.service;

import java.time.LocalDateTime;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hosting.restapi.dto.PasswordUpdateRequest;
import com.hosting.restapi.dto.UserDTO;
import com.hosting.restapi.entity.User;
import com.hosting.restapi.repository.UserRepository;

@Service
public class UserService {
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	
	
	public UserDetailsService userDetailsService() {
		return new UserDetailsService() {
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				return userRepository.findByEmail(username)
						.orElseThrow(()-> new UsernameNotFoundException("email not found"));
			}
		};
	}
	
	public User saveUser(User newUser) {
		if(newUser.getId() == null) {
			newUser.setCreatedAt(LocalDateTime.now());
		}
		newUser.setUpdatedAt(LocalDateTime.now());
		return this.userRepository.save(newUser);
	}
	
	public User updateUser(UserDTO updatedDTO) {
		
		User toUpdate = this.userRepository.findById(updatedDTO.getId()).get();
		toUpdate.setFirstName(updatedDTO.getFirstName());
		toUpdate.setLastName(updatedDTO.getLastName());
		toUpdate.setPhoneNumber(updatedDTO.getPhoneNumber());
		toUpdate.setEmergencyPhoneNubmer(updatedDTO.getEmergencyPhoneNubmer());
		toUpdate.setUpdatedAt(LocalDateTime.now());
		return this.userRepository.save(toUpdate);
		
	}
	
	public User updateUserEmail(UserDTO updatedDTO) {
		
		User toUpdate = this.userRepository.findById(updatedDTO.getId()).get();
		if(this.userRepository.findByEmail(updatedDTO.getEmail())!=null) 
			throw new IllegalArgumentException("Email already in use");
		toUpdate.setEmail(updatedDTO.getEmail());
		return this.userRepository.save(toUpdate);
		
	}
	
	public User updateUserPassword(PasswordUpdateRequest password) {
		
		User toUpdate = this.userRepository.findById(password.getUserId()).get();
		if(!toUpdate.getPassword().equals(passwordEncoder.encode(password.getOldPassword()))) 
			throw new IllegalArgumentException("Password does not match");
		toUpdate.setPassword(passwordEncoder.encode(password.getNewPassword()));
		return this.userRepository.save(toUpdate);

	}
	
}

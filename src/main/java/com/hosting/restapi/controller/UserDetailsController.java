package com.hosting.restapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hosting.restapi.dto.PasswordResetRequest;
import com.hosting.restapi.dto.PasswordUpdateRequest;
import com.hosting.restapi.dto.UserDTO;
import com.hosting.restapi.entity.User;
import com.hosting.restapi.repository.UserRepository;
import com.hosting.restapi.service.PasswordResetService;
import com.hosting.restapi.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "*")
public class UserDetailsController {
	
	@Autowired
	private final UserRepository userRepository;
	@Autowired
	private final UserService userService;
	@Autowired 
	private final PasswordResetService passwordResetService;
	
	public UserDetailsController(UserRepository userRepository, UserService userService, PasswordResetService passwordResetService) {
		this.userRepository = userRepository;
		this.userService = userService;
		this.passwordResetService = passwordResetService;
	}

	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public Optional<User> usersEndPoint(@RequestParam(value="email") String email) {
		return Optional.ofNullable(this.userRepository.findByEmail(email)).get();
	}

	@GetMapping("/admins")
	@PreAuthorize("hasRole('ADMIN')")
	public List<User> adminsEndPoint() {
		return Optional.ofNullable(this.userRepository.findAll()).get();
	}
	
	@PostMapping("/update-infos")
	@PreAuthorize("hasRole('USER')")
	public UserDTO updateAccountInfos(@RequestBody UserDTO user) {
		return new UserDTO(this.userService.updateUser(user));
	}
	
	@PostMapping("/update-email")
	@PreAuthorize("hasRole('USER')")
	public UserDTO updateUserEmail(@RequestBody UserDTO user) {
		return new UserDTO(this.userService.updateUserEmail(user));
	}
	
	@PostMapping("/update-password")
	@PreAuthorize("hasRole('USER')")
	public UserDTO updateUserEmail(@RequestBody PasswordUpdateRequest password) {
		return new UserDTO(this.userService.updateUserPassword(password));
	}
	
	@PostMapping("/update-phone-number")
	public UserDTO updateUserPhoneNumber(@RequestBody String phoneNumber) {
		return null;
	}
	
	
}

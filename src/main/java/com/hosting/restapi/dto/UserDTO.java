package com.hosting.restapi.dto;

import java.time.LocalDateTime;

import com.hosting.restapi.entity.Role;
import com.hosting.restapi.entity.User;



public class UserDTO {

	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String emergencyPhoneNubmer;
	private String address;
	private boolean isVerified;
	private Role role;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	public UserDTO() {
		super();
	}
	
	public UserDTO(User user) {
		setId(user.getId());
		setFirstName(user.getFirstName());
		setLastName(user.getLastName());
		setEmail(user.getEmail());
		setEmergencyPhoneNubmer(user.getEmergencyPhoneNubmer());
		setAddress(user.getAddress());
		setVerified(user.isVerified());
		setRole(user.getRole());
		setCreatedAt(user.getCreatedAt());
		setUpdatedAt(user.getUpdatedAt());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmergencyPhoneNubmer() {
		return emergencyPhoneNubmer;
	}

	public void setEmergencyPhoneNubmer(String emergencyPhoneNubmer) {
		this.emergencyPhoneNubmer = emergencyPhoneNubmer;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isVerified() {
		return isVerified;
	}

	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
}

package com.hosting.restapi.entity;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class User implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4544016799263577940L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String firstName;
	private String lastName;
	@Column(unique=true)
	private String email;
	private String phoneNumber;
	private String emergencyPhoneNubmer;
	private String address;
	private Boolean isVerified = false;
	@JsonIgnore
	private String password;
	@Enumerated(EnumType.STRING)
	private Role role;
	private Boolean isHost = false;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
//	@OneToMany(mappedBy = "guest", cascade = CascadeType.ALL, orphanRemoval = true)
//	@JoinColumn(name="guest_id")
//	private List<Reservation> reservations = new ArrayList<>();
//	
//	@OneToMany(mappedBy = "host", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
//	@JoinColumn(name="host_id")
//	private List<Offer> offers;
	
	
	
	public User() {
		super();
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


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
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


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role.name()));
	}


	@Override
	public String getUsername() {
		return email;
	}


	@Override
	public boolean isAccountNonExpired() {
		return true;
	}


	@Override
	public boolean isAccountNonLocked() {
		return true;
	}


	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}


	@Override
	public boolean isEnabled() {
		return true;
	}
	
	
	
	public Boolean getIsVerified() {
		return isVerified;
	}


	public void setIsVerified(Boolean isVerified) {
		this.isVerified = isVerified;
	}


	public Boolean getIsHost() {
		return isHost;
	}


	public void setIsHost(Boolean isHost) {
		this.isHost = isHost;
	}


//	public List<Reservation> getReservations() {
//		return reservations;
//	}
//
//
//	public void setReservations(List<Reservation> reservations) {
//		this.reservations = reservations;
//	}
//	
//	public void addReservation(Reservation reservation) {
//        this.reservations.add(reservation);
//        reservation.setGuest(this);
//    }
// 
//    public void removeReservation(Reservation reservation) {
//        this.reservations.remove(reservation);
//        reservation.setGuest(null);
//    }
//    
//    public void addOffer(Offer offer) {
//        this.offers.add(offer);
//        offer.setHost(this);
//    }
// 
//    public void removeOffer(Offer offer) {
//        this.offers.remove(offer);
//        offer.setHost(this);
//    }
//
//
//	public List<Offer> getOffers() {
//		return offers;
//	}
//
//
//	public void setOffers(List<Offer> offers) {
//		this.offers = offers;
//	}
//

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", role="
				+ role + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}
	
}

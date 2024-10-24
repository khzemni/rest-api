package com.hosting.restapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hosting.restapi.entity.Reservation;
import com.hosting.restapi.entity.User;
import com.hosting.restapi.repository.ReservationRepository;

@RestController
@RequestMapping("/api/v1/reservations")
@CrossOrigin(origins = "*")
public class ReservationController {
	
	@Autowired
	private final ReservationRepository reservationRepository;
	
	ReservationController(ReservationRepository reservationRepository) {
		this.reservationRepository = reservationRepository;
	}
	
	@GetMapping("/host")
	public List<Reservation> getHostReservation() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.isAuthenticated()) {
            User user = (User) authentication.getPrincipal();
            return this.reservationRepository.findByOfferHostId(user.getId());
		} else throw new RuntimeException("wrong request");
	}
	
	@GetMapping("/guest")
	public List<Reservation> getGuestReservation() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.isAuthenticated()) {
            User user = (User) authentication.getPrincipal();
            return this.reservationRepository.findByGuestId(user.getId());
		} else throw new RuntimeException("wrong request");
	}

}

package com.hosting.restapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hosting.restapi.entity.Reservation;
import com.hosting.restapi.service.OfferReservationService;

@RestController
@RequestMapping("/api/v1/offer-reservation")
@CrossOrigin(origins = "*")
public class OfferReservationController {
	
	@Autowired
	private final OfferReservationService reservationservice;
	
	OfferReservationController(OfferReservationService reservationservice) {
		this.reservationservice = reservationservice;
	}
	
	@PostMapping
	public Reservation reserveOffer(@RequestParam(name = "offerId", required = true) Long offerId, @RequestBody Reservation reservation) {
		return this.reservationservice.validateReservation(reservation, offerId);
	}
	
	@GetMapping
	public List<Reservation> getOfferReservations(@RequestParam(name="offerId", required = true) Long offerId) {
		return this.reservationservice.getOfferReservation(offerId);
	}

}

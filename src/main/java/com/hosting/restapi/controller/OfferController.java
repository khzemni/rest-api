package com.hosting.restapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hosting.restapi.entity.Offer;
import com.hosting.restapi.service.OfferService;

@RestController
@RequestMapping("/api/v1/offers")
@CrossOrigin(origins = "*")
public class OfferController {
	
	@Autowired
	private final OfferService offerService;
	
	OfferController(OfferService offerService) {
		this.offerService = offerService;
	}
	
	@GetMapping
	List<Offer> listAvailableOffers() {
		return this.offerService.ListAvailableOffers();
	}
	
	@GetMapping("/{offerId}")
	Offer listAvailableOffers(@PathVariable(name = "offerId", required = true) String offerId) {
		return this.offerService.getOfferById(Long.valueOf(offerId));
	}
	
	@GetMapping("/host")
	Offer listHostOffers(String offerId) {
		return this.offerService.getOfferById(Long.valueOf(offerId));
	}

}

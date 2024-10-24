package com.hosting.restapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hosting.restapi.entity.Offer;
import com.hosting.restapi.repository.OfferRepository;
import com.hosting.restapi.service.OfferCreationService;

@RestController
@RequestMapping("/api/v1/offer-creation")
@CrossOrigin(origins = "*")
public class OfferCreationController {
	
	@Autowired
	private final OfferCreationService offerCreationService;
	@Autowired
	private final OfferRepository offerRepository;
	
	OfferCreationController(OfferCreationService offerCreationService, OfferRepository offerRepository) {
		this.offerCreationService = offerCreationService;
		this.offerRepository = offerRepository;
	}
	
	@PostMapping
	public Offer saveProgress(@RequestBody Offer offer) {
		return this.offerCreationService.saveOfferProgress(offer);
	}
	
	@GetMapping
	public List<Offer> ListInProgressOffers() {
		return this.offerRepository.findByCreationFinished(false);
	}

}

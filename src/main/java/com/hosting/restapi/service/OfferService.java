package com.hosting.restapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hosting.restapi.entity.Offer;
import com.hosting.restapi.repository.OfferRepository;

@Service
public class OfferService {
	
	@Autowired
	private OfferRepository offerRepository;
	
	OfferService(OfferRepository offerRepository) {
		this.offerRepository = offerRepository;
	}
	
	public List<Offer> ListAvailableOffers() {
		return this.offerRepository.findByCreationFinished(true);
	}

	public Offer getOfferById(Long offerId) {
		return this.offerRepository.findById(offerId)
				.orElseThrow(()-> new RuntimeException("Offer with id :"+offerId+" is not available"));
	}
	
	public List<Offer> getHostOffers() {
		return this.offerRepository.findByHostId(1L);
	}

}

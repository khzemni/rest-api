package com.hosting.restapi.service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.hosting.restapi.entity.Address;
import com.hosting.restapi.entity.Lodging;
import com.hosting.restapi.entity.LodgingCriteria;
import com.hosting.restapi.entity.Offer;
import com.hosting.restapi.entity.OfferReservationOption;
import com.hosting.restapi.entity.User;
import com.hosting.restapi.repository.AddressRepository;
import com.hosting.restapi.repository.LodgingCriteriaRepository;
import com.hosting.restapi.repository.LodgingRepository;
import com.hosting.restapi.repository.OfferImageRepository;
import com.hosting.restapi.repository.OfferRepository;
import com.hosting.restapi.repository.OfferReservationOptionRepository;

import jakarta.transaction.Transactional;

@Service
public class OfferCreationService {
	
	@Autowired
	private final OfferRepository offerRepository;
	@Autowired
	private final LodgingRepository lodgingRepository;
	@Autowired
	private final AddressRepository addressRepository;
	@Autowired
	private LodgingCriteriaRepository lodgingCriteriaRepository;
	@Autowired 
	private OfferReservationOptionRepository offerReservationOptionRepository;
	
	
	
	OfferCreationService(
			OfferRepository offerRepository,
			LodgingRepository lodgingRepository,
			AddressRepository addressRepository,
			OfferImageRepository offerImageRepository
			) {
		this.offerRepository = offerRepository;
		this.lodgingRepository = lodgingRepository;
		this.addressRepository = addressRepository;
	}
	
	public Offer saveOfferProgress(Offer offer) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.isAuthenticated()) {
            User user = (User) authentication.getPrincipal();
            offer.setHost(user);
		} else throw new RuntimeException("wrong request");
		
		Offer toSave = null;
		offer.setUpdatedAt(LocalDateTime.now());
		switch(offer.getFinishedSteps()) {
			case 1 : 
			{
				toSave = saveOfferLodgingType(offer);
				break;
			}
			
			case 2 : 
			{
				toSave = saveOfferType(offer);
				break;
			}
			
			case 3 : 
			{
				toSave = saveOfferLodgingLocation(offer);
				break;
			}
			
			case 4 : 
			{
				toSave = saveOfferLodgingLocation(offer);
				break;
			}
			
			case 5 : 
			{
				toSave = saveOfferLogingCapacity(offer);
				break;
			}
			
			case 6 : 
			{
				toSave = saveOfferLodgingCriterias(offer);
				break;
			}
			
			case 7 : 
			{
				toSave = saveOfferTitleAndDescription(offer);
			}
			
			case 8 : 
			{
				toSave = saveOfferTitleAndDescription(offer);
				break;
			}
			
			case 9 : 
			{
				toSave = saveOfferReservationOptionsAndPrice(offer);
				break;
			}
			case 10 : 
			{
				toSave = finishAndPublish(offer);
				break;
			}
			default :break;
		}
		return toSave;
	}

	private Offer finishAndPublish(Offer offer) {
//		offer.setIsPublished(true); // to change 
		offer.setCreationFinished(true);
		return this.offerRepository.save(offer);
	}

	private Offer saveOfferReservationOptionsAndPrice(Offer offer) {
	    Set<OfferReservationOption> optionsCopy = new HashSet<>(offer.getReservationOptions());
	    offer.getReservationOptions().clear();
	    for (OfferReservationOption option : optionsCopy) {
	        option.setOffer(offer);
	        OfferReservationOption saved = this.offerReservationOptionRepository.save(option);
	        offer.getReservationOptions().add(saved);
	    }
	    return this.offerRepository.save(offer);
	}


	@Transactional
	private Offer saveOfferLodgingCriterias(Offer offer) {
	    Set<LodgingCriteria> criteriasCopy = new HashSet<>(offer.getLodging().getCriterias());
	    Set<LodgingCriteria> updatedCriterias = new HashSet<>();
	    Lodging lodging = offer.getLodging();
	    lodging.getCriterias().clear();
	    for (LodgingCriteria criteria : criteriasCopy) {
	        criteria.setLodging(offer.getLodging());
	        LodgingCriteria saved = this.lodgingCriteriaRepository.save(criteria);
	        updatedCriterias.add(saved);
	    }
	    
	    lodging.setCriterias(updatedCriterias);
	    Lodging savedLodiging = this.lodgingRepository.save(lodging);
	    offer.setLodging(savedLodiging);
	    return this.offerRepository.save(offer);
	}
	
//	private Offer saveOfferLodgingCriterias(Offer offer) {
//	    // Create a copy of the existing criteria set
//	    Set<LodgingCriteria> criteriasCopy = new HashSet<>(offer.getLodging().getCriterias());
//	    
//	    // Clear the criteria from the original lodging object
//	    offer.getLodging().getCriterias().clear();
//
//	    // Persist each criteria from the copy
//	    Set<LodgingCriteria> updatedCriterias = new HashSet<>();
//	    for (LodgingCriteria criteria : criteriasCopy) {
//	        criteria.setLodging(offer.getLodging());
//	        LodgingCriteria saved = this.lodgingCriteriaRepository.save(criteria);
//	        updatedCriterias.add(saved);
//	    }
//
//	    // Set the updated criteria set back to the lodging object
//	    offer.getLodging().setCriterias(updatedCriterias);
//	    
//	    // Save the updated lodging object
//	    Lodging savedLodging = this.lodgingRepository.save(offer.getLodging());
//	    
//	    // Update the offer with the saved lodging
//	    offer.setLodging(savedLodging);
//	    
//	    // Save the offer (which now includes the updated lodging object)
//	    return this.offerRepository.save(offer);
//	}


	private Offer saveOfferLogingCapacity(Offer offer) {
		Lodging toSave = this.lodgingRepository.save(offer.getLodging());
		offer.setLodging(toSave);
		return this.offerRepository.save(offer);
	}

	private Offer saveOfferTitleAndDescription(Offer offer) {
		return this.offerRepository.save(offer);
	}

	private Offer saveOfferLodgingLocation(Offer offer) {
		Address address = this.addressRepository.save(offer.getLodging().getAddress());
		address.setLodging(offer.getLodging());
		Lodging lodging = offer.getLodging();
		lodging.setAddress(address);
		this.lodgingRepository.save(lodging);
		return this.offerRepository.save(offer);
	}

	private Offer saveOfferType(Offer offer) {
		return this.offerRepository.save(offer);
	}

	private Offer saveOfferLodgingType(Offer offer) {
		Lodging toSave = this.lodgingRepository.save(offer.getLodging());
		toSave.setOffer(offer);
		offer.setLodging(toSave);
		return this.offerRepository.save(offer);
	}
	
	

}

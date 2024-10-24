package com.hosting.restapi.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.hosting.restapi.entity.Offer;
import com.hosting.restapi.entity.Reservation;
import com.hosting.restapi.entity.ReservationSpan;
import com.hosting.restapi.entity.TemporalSpan;
import com.hosting.restapi.entity.User;
import com.hosting.restapi.repository.OfferRepository;
import com.hosting.restapi.repository.ReservationRepository;

@Service
public class OfferReservationService {
	
	@Autowired
	private final ReservationRepository reservationRepository;
	@Autowired
	private final OfferRepository offerRepository;
	
	OfferReservationService(
			ReservationRepository reservationRepository, 
			OfferRepository offerRepository) {
		this.reservationRepository = reservationRepository;
		this.offerRepository = offerRepository;
	}
	
	public Reservation validateReservation(Reservation reservation, Long offerId) {
		Offer offer = offerRepository.findById(offerId).orElseThrow( () -> new RuntimeException("No offer found !"));
		offer.getReservations().add(reservation);
		Offer savedOffer = offerRepository.save(offer);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.isAuthenticated()) {
            User user = (User) authentication.getPrincipal();
            reservation.setGuest(user);
		} else throw new RuntimeException("wrong request");
		reservation.setOffer(savedOffer);
		return this.reservationRepository.save(reservation);
		
	}
	
	private boolean isOfferAvailable(TemporalSpan span, LocalDateTime start, LocalDateTime end, int quantity, Offer offer) {
		List<Reservation> reservations = this.reservationRepository.findByOfferId(offer.getId());
		for(Reservation reservation : reservations) {
			if(!span.equals(TemporalSpan.HOURLY)) {
				boolean isOccupied = reservation.getEnd().isAfter(start) && reservation.getStart().isBefore(end);
				if (isOccupied)
					return isOccupied;
			}
		}
		return true;
	}
	
	private boolean isGuestAvailable(ReservationSpan span, LocalDateTime start, LocalDateTime end, int quantity, User guest) {
		List<Reservation> reservations = this.reservationRepository.findByGuestId(guest.getId());
		for(Reservation reservation : reservations) {
			if(!span.equals(TemporalSpan.HOURLY)) {
				boolean isOccupied = reservation.getEnd().isAfter(start) && reservation.getStart().isBefore(end);
				if (isOccupied)
					return isOccupied;
			}
		}
		return true;
	}
	
	public List<Reservation> getOfferReservation(Long offerId) {
		return this.reservationRepository.findByOfferId(offerId);
	}

}

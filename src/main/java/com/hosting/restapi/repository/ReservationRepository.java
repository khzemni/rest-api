package com.hosting.restapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hosting.restapi.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
	
	List<Reservation> findByOfferId(Long offerId);
	List<Reservation> findByGuestId(Long guestId);
	List<Reservation> findByOfferHostId(Long hostId);

}

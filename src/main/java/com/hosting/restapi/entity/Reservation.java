package com.hosting.restapi.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private LocalDateTime start;
	private LocalDateTime end;
	
	private Integer quantity;
	private Integer numberOfVisitors;
	
	
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "offer_id", nullable = true)
	@JsonIgnore
	private Offer offer;
	
	@ManyToOne()
	@JoinColumn(name = "offer_reservation_option", referencedColumnName = "id")
	private OfferReservationOption reservationOption;
	
	@ManyToOne(optional = true)
	@JoinColumn(name = "guest_id", nullable = true)
	@JsonIgnore
	private User guest;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getStart() {
		return start;
	}

	public void setStart(LocalDateTime start) {
		this.start = start;
	}

	public LocalDateTime getEnd() {
		return end;
	}

	public void setEnd(LocalDateTime end) {
		this.end = end;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getNumberOfVisitors() {
		return numberOfVisitors;
	}

	public void setNumberOfVisitors(Integer numberOfVisitors) {
		this.numberOfVisitors = numberOfVisitors;
	}

	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}

	public OfferReservationOption getReservationOption() {
		return reservationOption;
	}

	public void setReservationOption(OfferReservationOption reservationOption) {
		this.reservationOption = reservationOption;
	}

	public User getGuest() {
		return guest;
	}

	public void setGuest(User guest) {
		this.guest = guest;
	}

	@Override
	public String toString() {
		return "Reservation [id=" + id + ", start=" + start + ", end=" + end + ", quantity=" + quantity + ", offer="
				+ offer + ", reservationOption=" + reservationOption + "]";
	}
	
}

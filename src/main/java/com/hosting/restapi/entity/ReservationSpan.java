package com.hosting.restapi.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

//@Entity
public class ReservationSpan {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Enumerated(EnumType.STRING)
	private TemporalSpan span;
	@JsonIgnore
	@OneToMany(mappedBy = "span", cascade= CascadeType.ALL)
	Set<OfferReservationOption> offerReservationOption;
	
	public ReservationSpan() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TemporalSpan getSpan() {
		return span;
	}

	public void setSpan(TemporalSpan span) {
		this.span = span;
	}

	public Set<OfferReservationOption> getOfferReservationOption() {
		return offerReservationOption;
	}

	public void setOfferReservationOption(Set<OfferReservationOption> offerReservationOption) {
		this.offerReservationOption = offerReservationOption;
	}
	
}

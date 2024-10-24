package com.hosting.restapi.entity;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class OfferReservationOption {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private BigDecimal price;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "offer_id")
	private Offer offer;
	
	@Enumerated(EnumType.STRING)
	private TemporalSpan span;
	
	private Integer minimumUnit;
	
	
	public OfferReservationOption() {
		super();
	}
	
	OfferReservationOption(TemporalSpan span, BigDecimal price) {
		this.price = price;
		this.span = span;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Offer getOffer() {
		return offer;
	}
	public void setOffer(Offer offer) {
		this.offer = offer;
	}
	public TemporalSpan getSpan() {
		return span;
	}
	public void setSpan(TemporalSpan span) {
		this.span = span;
	}
	
	public Integer getMinimumUnit() {
		return minimumUnit;
	}

	public void setMinimumUnit(Integer minimumUnit) {
		this.minimumUnit = minimumUnit;
	}

	@Override
	public String toString() {
		return "OfferReservationOption [id=" + id + ", price=" + price + ", offer=" + offer + ", span=" + span
				+ ", minimumUnit=" + minimumUnit + "]";
	}

	
}

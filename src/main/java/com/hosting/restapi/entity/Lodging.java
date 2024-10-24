package com.hosting.restapi.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Lodging {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@OneToOne
	@JoinColumn(name="address_id")
	@JsonIgnoreProperties(value = "lodging")
	private Address address;
	@Column(name = "lodging_type")
	private String type;
	private Integer capacity;
	private Integer numberOfRooms;
	private Integer numberOfBeds;
	private Integer numberOfBathrooms;
	@OneToOne(mappedBy = "lodging")
	private Offer offer;
	@OneToMany(mappedBy = "lodging", cascade = CascadeType.ALL, orphanRemoval = true)
	Set<LodgingCriteria> criterias;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getCapacity() {
		return capacity;
	}
	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}
	public Integer getNumberOfRooms() {
		return numberOfRooms;
	}
	public void setNumberOfRooms(Integer numberOfRooms) {
		this.numberOfRooms = numberOfRooms;
	}
	public Integer getNumberOfBeds() {
		return numberOfBeds;
	}
	public void setNumberOfBeds(Integer numberOfBeds) {
		this.numberOfBeds = numberOfBeds;
	}
	public Integer getNumberOfBathrooms() {
		return numberOfBathrooms;
	}
	public void setNumberOfBathrooms(Integer numberOfBathrooms) {
		this.numberOfBathrooms = numberOfBathrooms;
	}
	public Offer getOffer() {
		return offer;
	}
	public void setOffer(Offer offer) {
		this.offer = offer;
	}
	
	public Set<LodgingCriteria> getCriterias() {
		return criterias;
	}
	public void setCriterias(Set<LodgingCriteria> criterias) {
		this.criterias = criterias;
	}
	
	@Override
	public String toString() {
		return "Lodging [id=" + id + ", type=" + type + ", capacity=" + capacity + ", numberOfRooms=" + numberOfRooms
				+ ", numberOfBeds=" + numberOfBeds + ", numberOfBathrooms=" + numberOfBathrooms + ", offer=" + offer
				+  "criterias = " + criterias + "]";
	}
	
	
	
}

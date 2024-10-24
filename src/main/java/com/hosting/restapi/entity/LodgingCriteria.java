package com.hosting.restapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne; 

@Entity
public class LodgingCriteria { 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "lodging_id")
	private Lodging lodging;
	@ManyToOne
	@JoinColumn(name = "criteria_id")
	private Criteria criteria;
	private String description;
	
	public LodgingCriteria() {
		super();
	}
	
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Lodging getLodging() {
		return lodging;
	}


	public void setLodging(Lodging lodging) {
		this.lodging = lodging;
	}


	public Criteria getCriteria() {
		return criteria;
	}


	public void setCriteria(Criteria criteria) {
		this.criteria = criteria;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	@Override
	public String toString() {
		return "LodgingCriteria [id=" + id + ", lodging=" + lodging + ", criteria=" + criteria + ", description="
				+ description + "]";
	}
}

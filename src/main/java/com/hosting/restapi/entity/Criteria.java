package com.hosting.restapi.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Criteria {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne
	private CriteriaCategory category;
	private String iconPath;
	private String name;
	@JsonIgnore
	@OneToMany(mappedBy = "criteria", orphanRemoval = true)
	Set<LodgingCriteria> lodgingCriterias;
	
	
	public Criteria() {
		super();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CriteriaCategory getCategory() {
		return category;
	}

	public void setCategory(CriteriaCategory category) {
		this.category = category;
	}

	public String getIconPath() {
		return iconPath;
	}

	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<LodgingCriteria> getLodgingCriterias() {
		return lodgingCriterias;
	}

	public void setLodgingCriterias(Set<LodgingCriteria> lodgingCriterias) {
		this.lodgingCriterias = lodgingCriterias;
	}

	@Override
	public String toString() {
		return "Criteria [id=" + id + ", category=" + category + ", iconPath=" + iconPath + ", name=" + name
				+ ", lodgingCriterias=" + lodgingCriterias + "]";
	}

	public Criteria(Long id, String name, CriteriaCategory category) {
		super();
		this.id = id;
		this.category = category;
		this.name = name;
//		this.lodgingCriterias = lodgingCriterias;
	}
	
	
	
	
}

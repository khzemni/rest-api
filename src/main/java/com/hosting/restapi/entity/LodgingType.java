package com.hosting.restapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class LodgingType {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String name;
	private String iconPath;
	public LodgingType() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIconPath() {
		return iconPath;
	}
	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}
	@Override
	public String toString() {
		return "LodgingType [id=" + id + ", name=" + name + ", iconPath=" + iconPath + "]";
	}
	public LodgingType(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	
	
}

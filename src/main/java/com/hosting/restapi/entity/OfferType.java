package com.hosting.restapi.entity;

public enum OfferType {
	ENTIRE("Entire lodging"), 
	PRIVATE_SPACE("Private space in a shared lodging"), 
	SHARED_SPACE("Shared space/lodging");

	private final String stringValue;
	
	private OfferType(String stringValue) {
		this.stringValue = stringValue;
	}
	@Override
	public String toString() {
		return this.stringValue;
	}
}

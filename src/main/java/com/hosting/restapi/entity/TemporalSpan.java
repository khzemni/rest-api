package com.hosting.restapi.entity;

public enum TemporalSpan {
	HOURLY("Reservation per hour"),
	NIGHTLY("Reservation per night"),
	WEEKLY("Weekly reservation"),
	MONTHLY("Monthly reservation or rental");
	
	private final String stringValue;
	
	private TemporalSpan(String stringValue) {
		this.stringValue = stringValue;
	}
	@Override
	public String toString() {
		return this.stringValue;
	}
}

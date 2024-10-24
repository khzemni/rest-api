package com.hosting.restapi.stripe;

import java.math.BigDecimal;

public class PaymentIntentDTO {
	
	private String description;
	private BigDecimal amount;
	private Currency currency;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Currency getCurrency() {
		return currency;
	}
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	
}

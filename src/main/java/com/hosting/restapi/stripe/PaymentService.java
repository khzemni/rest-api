package com.hosting.restapi.stripe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

@Service
public class PaymentService {
	
	@Value("${stripe.api.secretKey}")
	String secretKey;

	public PaymentIntent payementIntent(PaymentIntentDTO paymentIntentDto) throws StripeException {
		Stripe.apiKey = secretKey;
		Map<String, Object> params = new HashMap<>();
		params.put("amount",paymentIntentDto.getAmount());
		params.put("currency",paymentIntentDto.getCurrency());
		params.put("description", paymentIntentDto.getDescription());
		
		ArrayList<Object> paymentMethodTypes = new ArrayList<>();
		paymentMethodTypes.add("card");
		
		params.put("payment_method_types", paymentMethodTypes);
		
		return PaymentIntent.create(params);
		
	}
	
	public PaymentIntent confirm(String intentId) throws StripeException {
		Stripe.apiKey = secretKey;
		PaymentIntent paymentIntent = PaymentIntent.retrieve(intentId);
//		Map<String, Object> params = new HashMap<>();
//		params.put("payment_method", "pm_card_visa");
		return paymentIntent.confirm();
		
	}
	
	public PaymentIntent cancel(String intentId) throws StripeException {
		Stripe.apiKey = secretKey;
		PaymentIntent paymentIntent = PaymentIntent.retrieve(intentId);
		return paymentIntent.cancel();
	}
}

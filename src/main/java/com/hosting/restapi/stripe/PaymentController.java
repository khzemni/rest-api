package com.hosting.restapi.stripe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

@RestController
@RequestMapping("/api/v1/payment")
@CrossOrigin(origins = "*")
public class PaymentController {
	
	@Autowired
	private final PaymentService paymentService;
	private static Gson gson = new Gson();
	
	PaymentController(PaymentService paymentService) {
		this.paymentService = paymentService;
	}
	
	@PostMapping("/paymentwIntent")
	public String paymentIntent(@RequestBody PaymentIntentDTO paymentIntentDTO) throws StripeException {
		return this.paymentService.payementIntent(paymentIntentDTO).toJson();
	}
	
	@PostMapping("/confirm/{intentId}")
	public ResponseEntity<String> confirm(@PathVariable(name = "intentId", required = true)  String intentId) throws StripeException {
		PaymentIntent paymentIntent = this.paymentService.confirm(intentId);
		String paymentIntentStr = paymentIntent.toJson();
		return new ResponseEntity<String>(paymentIntent.toString(), HttpStatus.OK);
		
	}
	
	@PostMapping("/cancel/{intentId}")
	public ResponseEntity<String> cancel(@PathVariable(name = "intentId", required = true)  String intentId) throws StripeException {
		PaymentIntent paymentIntent = this.paymentService.cancel(intentId);
		String paymentIntentStr = paymentIntent.toJson();
		return new ResponseEntity<String>(paymentIntentStr, HttpStatus.OK);
	}
	
	

}

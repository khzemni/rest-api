package com.hosting.restapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hosting.restapi.dto.MessageDTO;
import com.hosting.restapi.entity.Message;
import com.hosting.restapi.service.MessageService;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/message")
public class MessageController {
	
	private final MessageService messageService;
	
	MessageController(MessageService messageService) {
		this.messageService = messageService;
	}

	@PostMapping
	public ResponseEntity<String> sendMessage(@RequestBody MessageDTO message) {
		Message persisted = null;
		persisted = this.messageService.sendMessage(message);
		if(persisted!=null)
			return ResponseEntity.ok("Message sent succussfully at : " + persisted.getSentAt().toString());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send message");
	}
	
	@GetMapping
	public List<Message> listRecievedMessages() {
		return this.messageService.listRecievedMessages();
	}
	
}

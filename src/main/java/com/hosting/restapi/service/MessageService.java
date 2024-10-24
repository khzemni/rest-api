package com.hosting.restapi.service;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.hosting.restapi.dto.MessageDTO;
import com.hosting.restapi.entity.Message;
import com.hosting.restapi.entity.Offer;
import com.hosting.restapi.entity.User;
import com.hosting.restapi.repository.MessageRepository;
import com.hosting.restapi.repository.OfferRepository;
import com.hosting.restapi.repository.UserRepository;

@Service
public class MessageService {
	
	@Autowired
	private final MessageRepository messageRepository;
	@Autowired
	private final OfferRepository offerRepository;
	@Autowired
	private final UserRepository userRepository;
	
	MessageService(
			MessageRepository messageRepository,
			OfferRepository offerRepository,
			UserRepository userRepository
			) {
		this.messageRepository = messageRepository;
		this.offerRepository = offerRepository;
		this.userRepository = userRepository;
	}

	public Message sendMessage(MessageDTO message) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		User sender = null;
		if (authentication != null && authentication.isAuthenticated()) {
            sender = (User) authentication.getPrincipal();
		} else throw new RuntimeException("wrong request");
		
		User destination = this.userRepository.findById(message.destinationId())
				.orElseThrow(() -> new RuntimeException("Destination user is not valid"));
		
		Offer subject = this.offerRepository.findById(message.subjectId())
				.orElseThrow(() -> new RuntimeException("Subject of message is not valid"));
		
		Message responseTo = null;
		if(message.messageId()!=null) {
			responseTo = this.messageRepository.findById(message.messageId())
					.orElseThrow(() -> new RuntimeException("Message subject is not valid"));
		}
		
		Message toPersist = new Message();
		toPersist.setSender(sender);
		toPersist.setDestination(destination);
		toPersist.setSubject(subject);
		toPersist.setReponseTo(responseTo);
		toPersist.setSentAt(LocalDateTime.now());
		toPersist.setContent(message.content());
		toPersist.setAdditionalContent(message.additionalContent());
		
		return this.messageRepository.save(toPersist);
		
	}

	public List<Message> listRecievedMessages() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		User user = null;
		if (authentication != null && authentication.isAuthenticated()) {
            user = (User) authentication.getPrincipal();
		} else throw new RuntimeException("wrong request");
		
		return this.messageRepository.findByDestinationId(user.getId());
	}
	
	

}

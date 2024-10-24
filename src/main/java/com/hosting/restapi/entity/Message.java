package com.hosting.restapi.entity;


import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Message {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long Id;
	
//	private Long senderId;
//	private Long destinationId;
//	private Long offerId;
	
	@ManyToOne
	@JoinColumn(name = "sender_id")
	private User sender;
	
	@ManyToOne
	@JoinColumn(name = "destination_id")
	@JsonIgnore
	private User destination;
	
	@OneToOne
	@JoinColumn(name = "offer_id")
	private Offer subject;
	private String content;
	private String additionalContent;
	private LocalDateTime sentAt;
	
	@ManyToOne
	@JoinColumn(name = "message_id")
	private Message reponseTo;
	
	@Column(columnDefinition = "BIT DEFAULT 0")
	private Boolean isRead;
	
	public Message() {
		super();
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getDestination() {
		return destination;
	}

	public void setDestination(User destination) {
		this.destination = destination;
	}

	public Offer getSubject() {
		return subject;
	}

	public void setSubject(Offer subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAdditionalContent() {
		return additionalContent;
	}

	public void setAdditionalContent(String additionalContent) {
		this.additionalContent = additionalContent;
	}

	public LocalDateTime getSentAt() {
		return sentAt;
	}

	public void setSentAt(LocalDateTime sentAt) {
		this.sentAt = sentAt;
	}

	public Message getReponseTo() {
		return reponseTo;
	}

	public void setReponseTo(Message reponseTo) {
		this.reponseTo = reponseTo;
	}

	public Boolean getIsRead() {
		return isRead;
	}

	public void setRead(Boolean read) {
		this.isRead = read;
	}

	@Override
	public String toString() {
		return "Message [Id=" + Id + ", sender=" + sender + ", destination=" + destination + ", subject=" + subject
				+ ", content=" + content + ", additionalContent=" + additionalContent + ", sentAt=" + sentAt
				+ ", reponseTo=" + reponseTo + ", read=" + isRead + "]";
	}

	
}

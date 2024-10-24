package com.hosting.restapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hosting.restapi.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
	
	List<Message> findBySenderId(Long senderId);
	List<Message> findByDestinationId(Long destinationId);

}

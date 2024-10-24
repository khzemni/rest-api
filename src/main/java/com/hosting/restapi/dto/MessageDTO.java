package com.hosting.restapi.dto;

import java.time.LocalDateTime;

public record MessageDTO(
		Long id, 
		Long destinationId, 
		Long subjectId, 
		Long messageId,
		String content, 
		String additionalContent,
		LocalDateTime sentAt) {

}

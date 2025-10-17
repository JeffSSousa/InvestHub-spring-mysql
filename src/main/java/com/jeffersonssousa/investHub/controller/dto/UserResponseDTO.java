package com.jeffersonssousa.investHub.controller.dto;

import java.time.Instant;

import com.jeffersonssousa.investHub.entities.User;

public record UserResponseDTO(Long id, String username, String email, Instant criationTimestamp, Instant updateTimestamp) {
	
	public UserResponseDTO(User user) {
		this(user.getUserId(), user.getUsername(), user.getEmail(), user.getCriationTimestamp(), user.getUpdateTimestamp());
	}

}

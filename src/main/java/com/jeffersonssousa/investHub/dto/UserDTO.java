package com.jeffersonssousa.investHub.dto;

import com.jeffersonssousa.investHub.entities.User;

public class UserDTO {

	private String username;
	private String email;
	private String password;

	public UserDTO() {
	}

	public UserDTO(User user) {
		this.username = user.getUsername();
		this.email = user.getEmail();
		this.password = user.getPassword();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}

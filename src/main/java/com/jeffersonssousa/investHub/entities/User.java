package com.jeffersonssousa.investHub.entities;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;

	private String username;
	private String email;
	private String password;

	private Instant criationTimestamp;
	private Instant updateTimestamp;

	public User() {
	}

	public User(Long userId, String username, String email, String password, Instant criationTimestamp,
			Instant updateTimestamp) {
		this.userId = userId;
		this.username = username;
		this.email = email;
		this.password = password;
		this.criationTimestamp = criationTimestamp;
		this.updateTimestamp = updateTimestamp;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public Instant getCriationTimestamp() {
		return criationTimestamp;
	}

	public void setCriationTimestamp(Instant criationTimestamp) {
		this.criationTimestamp = criationTimestamp;
	}

	public Instant getUpdateTimestamp() {
		return updateTimestamp;
	}

	public void setUpdateTimestamp(Instant updateTimestamp) {
		this.updateTimestamp = updateTimestamp;
	}

}

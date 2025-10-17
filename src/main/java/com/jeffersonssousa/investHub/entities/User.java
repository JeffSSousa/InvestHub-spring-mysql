package com.jeffersonssousa.investHub.entities;

import java.time.Instant;
import java.util.List;

import com.jeffersonssousa.investHub.controller.dto.UserRequestDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;

	private String username;
	private String email;
	private String password;

	private Instant criationTimestamp;
	private Instant updateTimestamp;
	
	@OneToMany(mappedBy = "user")
	private List<Account> accounts;
	
	public User(UserRequestDTO dto) {
		this.username = dto.username();
		this.email = dto.email();
		this.password = dto.password();
		this.criationTimestamp = Instant.now();
	}
	
	
	
}

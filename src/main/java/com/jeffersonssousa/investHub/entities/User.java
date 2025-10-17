package com.jeffersonssousa.investHub.entities;

import java.time.Instant;
import java.util.List;

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
	
	public User(Long userId, String username, String email, String password, Instant criationTimestamp,
			Instant updateTimestamp) {
		this.userId = userId;
		this.username = username;
		this.email = email;
		this.password = password;
		this.criationTimestamp = criationTimestamp;
		this.updateTimestamp = updateTimestamp;
	}


	
	
	
}

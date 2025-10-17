package com.jeffersonssousa.investHub.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_accounts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Account {

	@Id
	@Column(name = "account_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long accountId;

	@Column(name = "description")
	private String description;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private BillingAddress billingAddress;

	@OneToMany(mappedBy = "account")
	private List<AccountStock> accountStocks;

}

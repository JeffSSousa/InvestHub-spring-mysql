package com.jeffersonssousa.investHub.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_billingaddress")
public class BillingAddress {

	@Id
	@Column(name = "account_id")
	private Long id;
	
	@OneToOne
	@MapsId
	@JoinColumn(name = "account_id")
	private Account account ;

	@Column(name = "street")
	private String street;

	@Column(name = "number")
	private Integer number;

	public BillingAddress() {
	}

	public BillingAddress(Long id, String street, Integer number) {
		this.id = id;
		this.street = street;
		this.number = number;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

}

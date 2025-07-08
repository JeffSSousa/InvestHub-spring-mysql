package com.jeffersonssousa.investHub.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class AccountStockId {

	@Column(name = "account_id")
	private Long accountId;

	@Column(name = "stock_id")
	private String stockId;

	public AccountStockId() {
	}

	public AccountStockId(Long accountId, String sotckId) {
		this.accountId = accountId;
		this.stockId = sotckId;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getSotckId() {
		return stockId;
	}

	public void setSotckId(String sotckId) {
		this.stockId = sotckId;
	}

}

package com.jeffersonssousa.investHub.controller.dto;

public class AccountResponseDTO {

	private Long accountId;
	private String description;

	public AccountResponseDTO() {
	}

	public AccountResponseDTO(Long accountId, String description) {
		this.accountId = accountId;
		this.description = description;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}

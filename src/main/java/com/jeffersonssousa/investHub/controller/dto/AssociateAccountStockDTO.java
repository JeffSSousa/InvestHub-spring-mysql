package com.jeffersonssousa.investHub.controller.dto;

public class AssociateAccountStockDTO {

	private String stockId;
	private Integer quantity;

	public AssociateAccountStockDTO() {
	}

	public AssociateAccountStockDTO(String stockId, Integer quantity) {
		this.stockId = stockId;
		this.quantity = quantity;
	}

	public String getStockId() {
		return stockId;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}

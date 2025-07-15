package com.jeffersonssousa.investHub.controller.dto;

public class AccountStockResponseDTO {
	
	private String stockId;
	private Integer quantity;
	private Double total;
	
	public AccountStockResponseDTO() {
	}

	public AccountStockResponseDTO(String stockId, Integer quantity, Double total) {
		this.stockId = stockId;
		this.quantity = quantity;
		this.total = total;
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

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}
}

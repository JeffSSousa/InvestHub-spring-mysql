package com.jeffersonssousa.investHub.controller.dto;

public class StockDTO {
	
	private String stockId;
	private String description;

	public StockDTO() {
	}

	public StockDTO(String stockId, String description) {
		this.stockId = stockId;
		this.description = description;
	}

	public String getStockId() {
		return stockId;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}

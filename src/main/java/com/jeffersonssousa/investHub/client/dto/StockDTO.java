package com.jeffersonssousa.investHub.client.dto;

public class StockDTO {

	private Double regularMarketPrice;

	public StockDTO() {
	}

	public StockDTO(Double regularMarketPrice) {
		this.regularMarketPrice = regularMarketPrice;
	}

	public Double getRegularMarketPrice() {
		return regularMarketPrice;
	}

	public void setRegularMarketPrice(Double regularMarketPrice) {
		this.regularMarketPrice = regularMarketPrice;
	}
	
}

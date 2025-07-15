package com.jeffersonssousa.investHub.client.dto;

import java.util.List;

import com.jeffersonssousa.investHub.client.StockDTO;


public class BrapiResponseDTO {

	private List<StockDTO> results ;

	public BrapiResponseDTO() {
	}

	public BrapiResponseDTO(List<StockDTO> results) {
		this.results = results;
	}

	public List<StockDTO> getResults() {
		return results;
	}

	public void setResults(List<StockDTO> results) {
		this.results = results;
	}
}

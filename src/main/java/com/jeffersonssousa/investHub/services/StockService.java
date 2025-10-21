package com.jeffersonssousa.investHub.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeffersonssousa.investHub.entities.Stock;
import com.jeffersonssousa.investHub.repository.StockRepository;

@Service
public class StockService {
	
	@Autowired
	StockRepository stockRepository;

	public Stock createStock(Stock stock) {
		return stockRepository.save(stock);
	}

}

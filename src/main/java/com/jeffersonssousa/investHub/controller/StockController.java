package com.jeffersonssousa.investHub.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jeffersonssousa.investHub.controller.dto.StockDTO;
import com.jeffersonssousa.investHub.entities.Stock;
import com.jeffersonssousa.investHub.services.StockService;

@RestController
@RequestMapping("/stocks")
public class StockController {
	
	@Autowired
	private StockService stockService;

	@PostMapping
	public ResponseEntity<Stock> createStock(@RequestBody  StockDTO stockDTO){
		Stock stock = stockService.fromDTO(stockDTO);
		
		stock = stockService.createStock(stock);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(stock.getStockId()).toUri();
		
		return ResponseEntity.created(uri).body(stock);
	}
	
}

package com.jeffersonssousa.investHub.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.jeffersonssousa.investHub.entities.Stock;
import com.jeffersonssousa.investHub.repository.StockRepository;

@ExtendWith(MockitoExtension.class)
public class StockServiceTest {

	@Mock
	private StockRepository repository;
	
	@InjectMocks
	private StockService service;

	@Captor
	private ArgumentCaptor<Stock> captor;
	
	@Test
	@DisplayName("Should create a stock with success")
	void shouldCreateStockWithSucess(){
		
		Stock stock = new Stock("BBAS3", "Banco do Brasil");
		when(repository.save(stock)).thenReturn(stock);
		
		service.createStock(stock);
		
		verify(repository, times(1)).save(captor.capture());
		Stock captured = captor.getValue();
		
		assertEquals(stock.getStockId(), captured.getStockId());
		assertEquals(stock.getDescription(), captured.getDescription());
		
		
	}
	
}

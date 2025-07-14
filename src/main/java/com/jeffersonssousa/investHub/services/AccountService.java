package com.jeffersonssousa.investHub.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeffersonssousa.investHub.controller.dto.AssociateAccountStockDTO;
import com.jeffersonssousa.investHub.entities.Account;
import com.jeffersonssousa.investHub.entities.AccountStock;
import com.jeffersonssousa.investHub.entities.AccountStockId;
import com.jeffersonssousa.investHub.entities.Stock;
import com.jeffersonssousa.investHub.repository.AccountRepository;
import com.jeffersonssousa.investHub.repository.AccountStockRepository;
import com.jeffersonssousa.investHub.repository.StockRepository;
import com.jeffersonssousa.investHub.services.exceptions.ControllerNotFoundException;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private StockRepository stockRepository;
	
	@Autowired
	private AccountStockRepository accountStockRepository;
	
	public void associateStock(Long accountId, AssociateAccountStockDTO dto) {
		
		Optional<Account> accountObj = accountRepository.findById(accountId);
	    Account account = accountObj.orElseThrow(() -> new ControllerNotFoundException(accountObj));
		
	    Optional<Stock> stockObj = stockRepository.findById(dto.getStockId());
	    Stock stock = stockObj.orElseThrow(() -> new ControllerNotFoundException(dto.getStockId()));
	    
	    AccountStockId id = new AccountStockId(account.getAccountId(), stock.getStockId());
	    AccountStock entity = new AccountStock(id, account, stock, dto.getQuantity());
	    
	    accountStockRepository.save(entity);
	}
	
}

package com.jeffersonssousa.investHub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jeffersonssousa.investHub.controller.dto.AccountStockResponseDTO;
import com.jeffersonssousa.investHub.controller.dto.AssociateAccountStockDTO;
import com.jeffersonssousa.investHub.services.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController {

	@Autowired
	AccountService accountService;
	
	@PostMapping("/{accountId}/stocks")
	public ResponseEntity<Void> associateStock(@PathVariable Long accountId,
			                                   @RequestBody AssociateAccountStockDTO dto){

		accountService.associateStock(accountId,dto);
		
		return ResponseEntity.ok().build();
	}
	
	
	@GetMapping("/{accountId}/stocks")
	public ResponseEntity<List<AccountStockResponseDTO>> listAccountStocks(@PathVariable Long accountId){
		List<AccountStockResponseDTO> list = accountService.listStocks(accountId);	
		return ResponseEntity.ok().body(list);
	}
	
}

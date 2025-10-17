package com.jeffersonssousa.investHub.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jeffersonssousa.investHub.controller.dto.AccountDTO;
import com.jeffersonssousa.investHub.controller.dto.AccountResponseDTO;
import com.jeffersonssousa.investHub.controller.dto.UserRequestDTO;
import com.jeffersonssousa.investHub.controller.dto.UserResponseDTO;
import com.jeffersonssousa.investHub.entities.Account;
import com.jeffersonssousa.investHub.entities.User;
import com.jeffersonssousa.investHub.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<Void> createUser(@RequestBody  UserRequestDTO dto){
		User user = new User(dto);
		user = userService.createUser(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getUserId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id){
		UserResponseDTO user = new UserResponseDTO(userService.getUserById(id));
		return ResponseEntity.ok().body(user);
	}
	
	@GetMapping
	public ResponseEntity<List<UserResponseDTO>> getUsers(){
		List<UserResponseDTO> list = userService.getUsers().stream().map(UserResponseDTO::new).toList();
		return ResponseEntity.ok().body(list);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<User> deleteById(@PathVariable Long id){
		userService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody UserRequestDTO dto){
		User user = new User(dto);
		userService.updateUserById(id, user);
		return ResponseEntity.noContent().build();
	}
	
	
	@PostMapping("/{userId}/accounts")
	public ResponseEntity<Void> createAccount(@PathVariable Long userId, @RequestBody  AccountDTO accountDTO){
		Account account = userService.createAccount(userId, accountDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{accountId}").buildAndExpand(account.getAccountId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping("/{userId}/accounts")
	public ResponseEntity<List<AccountResponseDTO>> listAccounts(@PathVariable Long userId){
		
		List<AccountResponseDTO> list = userService.listAccounts(userId);
		
		return ResponseEntity.ok().body(list);
	}
}

package com.jeffersonssousa.investHub.services;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeffersonssousa.investHub.controller.dto.AccountDTO;
import com.jeffersonssousa.investHub.controller.dto.AccountResponseDTO;
import com.jeffersonssousa.investHub.controller.dto.UserDTO;
import com.jeffersonssousa.investHub.entities.Account;
import com.jeffersonssousa.investHub.entities.BillingAddress;
import com.jeffersonssousa.investHub.entities.User;
import com.jeffersonssousa.investHub.repository.AccountRepository;
import com.jeffersonssousa.investHub.repository.BillingAddressRepository;
import com.jeffersonssousa.investHub.repository.UserRepository;
import com.jeffersonssousa.investHub.services.exceptions.ControllerNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private BillingAddressRepository billingAddressRepository;

	public User createUser(User user) {
		return userRepository.save(user);
	}

	public User getUserById(Long id) {
		Optional<User> obj = userRepository.findById(id);
		return obj.orElseThrow(() -> new ControllerNotFoundException(id));
	}

	public List<User> getUsers() {
		return userRepository.findAll();
	}

	public void deleteById(Long id) {
		if (!userRepository.existsById(id)) {
			throw new ControllerNotFoundException(id);
		}
		userRepository.deleteById(id);
	}

	public User updateUserById(Long id, User user) {
		try {
			User entity = userRepository.getReferenceById(id);
			updateUser(entity, user);
			return userRepository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ControllerNotFoundException(id);
		}

	}
	
	public Account createAccount(Long userId, AccountDTO accountDTO) {
		if(!userRepository.existsById(userId)) {
			throw new ControllerNotFoundException(userId);
		}
		
		Optional<User> user = userRepository.findById(userId);
		
		Account account = new Account(null, accountDTO.getDescription(), user.get(), null, new ArrayList<>());
		BillingAddress billingAddress = new BillingAddress(null, account , accountDTO.getStreet(), accountDTO.getNumber());
		account.setBillingAddress(billingAddress);
		
		Account newAccount = accountRepository.save(account);
		billingAddressRepository.save(billingAddress);

		
		return newAccount;
	}

	public List<AccountResponseDTO> listAccounts(Long userId) {
		Optional<User> user = userRepository.findById(userId);
		user.orElseThrow(() -> new ControllerNotFoundException(userId));
		
		return user.get()
		    .getAccounts()
		    .stream()
		    .map(ac -> new AccountResponseDTO(ac.getAccountId(), ac.getDescription()))
		    .toList();
	}
	
	public User fromUserDTO(UserDTO userDTO) {
		return new User(null, userDTO.getUsername(), userDTO.getEmail(), userDTO.getPassword(), Instant.now(), null);
	}

	private void updateUser(User entity, User user) {
		if (user.getUsername() != null) {
			entity.setUsername(user.getUsername());
		}
		if (user.getPassword() != null) {
			entity.setPassword(user.getPassword());
		}
		entity.setUpdateTimestamp(Instant.now());
	}

	
}

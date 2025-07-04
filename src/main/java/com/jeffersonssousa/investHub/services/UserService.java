package com.jeffersonssousa.investHub.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeffersonssousa.investHub.dto.UserDTO;
import com.jeffersonssousa.investHub.entities.User;
import com.jeffersonssousa.investHub.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User createUser(User user) {
		return userRepository.save(user);
	}
	
	public User getUserById(Long id) {
		Optional<User> obj = userRepository.findById(id);
		return obj.get();
	}
	
	public List<User> getUsers(){
		return userRepository.findAll();
	}
	
	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}

	
	public User fromDTO (UserDTO userDTO) {
		return new User(null, userDTO.getUsername(), userDTO.getEmail(), userDTO.getPassword(), Instant.now(), null);
	}
}

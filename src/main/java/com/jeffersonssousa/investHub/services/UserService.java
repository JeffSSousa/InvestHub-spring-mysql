package com.jeffersonssousa.investHub.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeffersonssousa.investHub.dto.UserDTO;
import com.jeffersonssousa.investHub.entities.User;
import com.jeffersonssousa.investHub.repository.UserRepository;
import com.jeffersonssousa.investHub.services.exceptions.ControllerNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

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

	public User fromDTO(UserDTO userDTO) {
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

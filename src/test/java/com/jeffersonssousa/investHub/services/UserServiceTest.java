package com.jeffersonssousa.investHub.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.jeffersonssousa.investHub.dto.UserDTO;
import com.jeffersonssousa.investHub.entities.User;
import com.jeffersonssousa.investHub.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	@Captor
	private ArgumentCaptor<User> userArgumentCaptor;
	
	@InjectMocks
	private UserService userService;

	@Nested
	class createUser {

		@Test
		@DisplayName("Should create a user with success")
		void shouldCreateAUser() {

			// Arrange
			UserDTO userDTO = new UserDTO("Username", "test@email.com", "password");
			User user = userService.fromDTO(userDTO);

			doReturn(user).when(userRepository).save(userArgumentCaptor.capture());
			UserDTO inputDTO = new UserDTO("Username", "test@email.com", "123");
			User input = userService.fromDTO(inputDTO);
			
			// Act
			User output = userService.createUser(input);

			// Assert
			assertNotNull(output);

			User userCaptured = userArgumentCaptor.getValue();

			assertEquals(input.getUsername(), userCaptured.getUsername());
			assertEquals(input.getEmail(), userCaptured.getEmail());
			assertEquals(input.getPassword(), userCaptured.getPassword());
			assertEquals(input.getCriationTimestamp(), userCaptured.getCriationTimestamp());
		}

		@Test
		@DisplayName("Should throw exception when error occurs")
		void shouldThrowExceptionWhenErrorOccurs() {

			// Arrange
			doThrow(new RuntimeException()).when(userRepository).save(any());
			User input = new User(null, "Username", "email@email.com", "123", null, null);

			// act & Assert
			assertThrows(RuntimeException.class, () -> userService.createUser(input));

		}
	}
	

}

package com.jeffersonssousa.investHub.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

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
import com.jeffersonssousa.investHub.services.exceptions.ControllerNotFoundException;

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

	@Nested
	class getUserById {

		@Test
		@DisplayName("Should get user by id with success when optional is present")
		void shouldGetUserByIdWithSuccessWhenOptionaIsPresent() {

			// Arrange
			Long id = 1L;
			User user = new User(id, "Username", "test@email.com", "password", Instant.now(), null);

			doReturn(Optional.of(user)).when(userRepository).findById(id);

			// act
			User output = userService.getUserById(id);

			// assert
			assertNotNull(output);
			assertEquals(user.getUserId(), output.getUserId());
			assertEquals(user.getEmail(), output.getEmail());
			assertEquals(user.getPassword(), output.getPassword());
			assertEquals(user.getCriationTimestamp(), output.getCriationTimestamp());
		}

		@Test
		@DisplayName("Should throw exception when user not found by id")
		void shouldThrowExceptionWhenUserNotFoundById() {

			// Arrange
			Long id = 1L;
			doReturn(Optional.empty()).when(userRepository).findById(id);

			// act & assert
			assertThrows(ControllerNotFoundException.class, () -> userService.getUserById(id));

		}
	}

	@Nested
	class getUsers {

		@Test
		@DisplayName("Should return all users success")
		void shouldReturnAllUsersWithSuccess() {

			// arrange
			User user = new User(null, "Username", "test@email.com", "password", Instant.now(), null);
			List<User> userList = List.of(user);
			
			doReturn(userList).when(userRepository).findAll();
			
			// act
			List<User> output = userService.getUsers();
			
			// assert
			assertNotNull(output);
			assertEquals(userList.size(), output.size());
			
		}
	}

}

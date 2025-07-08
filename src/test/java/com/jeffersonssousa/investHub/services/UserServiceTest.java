package com.jeffersonssousa.investHub.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

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

import com.jeffersonssousa.investHub.controller.dto.UserDTO;
import com.jeffersonssousa.investHub.entities.User;
import com.jeffersonssousa.investHub.repository.UserRepository;
import com.jeffersonssousa.investHub.services.exceptions.ControllerNotFoundException;

import jakarta.persistence.EntityNotFoundException;

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
			User user = userService.fromUserDTO(userDTO);

			doReturn(user).when(userRepository).save(userArgumentCaptor.capture());
			UserDTO inputDTO = new UserDTO("Username", "test@email.com", "123");
			User input = userService.fromUserDTO(inputDTO);

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

	@Nested
	class deleteById {

		@Test
		@DisplayName("Should delete user with success when user exists")
		void shouldDeleteUserWithSuccessWhenUserExists() {

			// Arrange
			Long id = 1L;

			doReturn(true).when(userRepository).existsById(id);
			doNothing().when(userRepository).deleteById(id);

			// act
			userService.deleteById(id);
			;

			// assert
			verify(userRepository).existsById(id);
			verify(userRepository).deleteById(id);
		}

		@Test
		@DisplayName("Should not delete user with success when user not exists")
		void shouldNotDeleteUserWithSuccessWhenUserNotExists() {

			// Arrange
			Long id = 1L;

			doReturn(false).when(userRepository).existsById(id);

			// Act & Assert
			assertThrows(ControllerNotFoundException.class, () -> userService.deleteById(id));

			verify(userRepository).existsById(id);
			verify(userRepository, never()).deleteById(id);

		}

	}

	@Nested
	class updateUserById {

		@Test
		@DisplayName("Should update user by id when user exists and username and password is filled")
		void shouldUpdateUserByIdWhenUserExistsAndUsernameAndPasswordIsFilled() {

			// Arrange
			Long id = 1L;
			User user = new User(id, "Username", "test@email.com", "password", Instant.now(), null);
			UserDTO userDTO = new UserDTO("newUsername", "newemail@email.com", "newPassword");
			User newUser = userService.fromUserDTO(userDTO);

			doReturn(user).when(userRepository).save(userArgumentCaptor.capture());
			doReturn(user).when(userRepository).getReferenceById(id);

			// act
			User output = userService.updateUserById(id, newUser);

			// assert
			User userCaptured = userArgumentCaptor.getValue();
			assertEquals(user.getUserId(), output.getUserId());
			assertEquals(output.getUsername(), userCaptured.getUsername());
			assertEquals(output.getPassword(), userCaptured.getPassword());
			assertNotNull(output.getUpdateTimestamp());

		}

		@Test
		@DisplayName("Should not update user when user not exists")
		void shouldNotUpdateUserWhenUserNotExists() {

			// Arrange
			Long id = 1L;
			UserDTO userDTO = new UserDTO("newUsername", "newemail@email.com", "newPassword");
			User newUser = userService.fromUserDTO(userDTO);

			doThrow(new EntityNotFoundException()).when(userRepository).getReferenceById(id);

			// Act & Assert
			assertThrows(ControllerNotFoundException.class, () -> userService.updateUserById(id, newUser));

			verify(userRepository).getReferenceById(id);
			verify(userRepository, never()).save(any());

		}
	}

}

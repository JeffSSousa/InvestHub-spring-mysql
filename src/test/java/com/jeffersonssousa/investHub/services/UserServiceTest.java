package com.jeffersonssousa.investHub.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
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

import com.jeffersonssousa.investHub.controller.dto.UserRequestDTO;
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
			UserRequestDTO dto = new UserRequestDTO("Test Test", "teste@test.com", "test123");
			User user = new User(dto);

			doReturn(user).when(userRepository).save(any(User.class));

			// Act
			userService.createUser(user);

			// Assert
			
			verify(userRepository, times(1)).save(userArgumentCaptor.capture());
			User userCaptured = userArgumentCaptor.getValue();

			assertEquals(user.getUsername(), userCaptured.getUsername());
			assertEquals(user.getEmail(), userCaptured.getEmail());
			assertEquals(user.getPassword(), userCaptured.getPassword());
			assertEquals(user.getCriationTimestamp(), userCaptured.getCriationTimestamp());
		}

		@Test
		@DisplayName("Should throw exception when error occurs")
		void shouldThrowExceptionWhenErrorOccurs() {

			// Arrange
			UserRequestDTO dto = new UserRequestDTO("Test Test", "teste@test.com", "test123");
			User user = new User(dto);
			
			doThrow(new RuntimeException()).when(userRepository).save(any());

			// act & Assert
			assertThrows(RuntimeException.class, () -> userService.createUser(user));

		}
	}

	@Nested
	class getUserById {

		@Test
		@DisplayName("Should get user by id with success when optional is present")
		void shouldGetUserByIdWithSuccessWhenOptionaIsPresent() {

			// Arrange
			Long id = 1L;
			User user = new User(id, "Username", "test@email.com", "password", Instant.now(), null, null);

			doReturn(Optional.of(user)).when(userRepository).findById(id);

			// act
			User output = userService.getUserById(id);

			// assert
			verify(userRepository, times(1)).findById(id);
			
			assertEquals(user.getUsername(), output.getUsername());
			assertEquals(user.getEmail(), output.getEmail());
			assertEquals(user.getPassword(), output.getPassword());
			assertNotNull(output.getCriationTimestamp());
			assertNull(output.getUpdateTimestamp());
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
			User user = new User(1L, "Username", "test@email.com", "password", Instant.now(), null, null);
			User user2 = new User(2L, "Username", "test@email.com", "password", Instant.now(), null, null);
			List<User> userList = List.of(user,user2);

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
			User user = new User(id, "Username", "test@email.com", "password", Instant.now(), null,null);
			UserRequestDTO dto = new UserRequestDTO("newUsername", "newemail@email.com", "newPassword");
			User updateUser = new User(dto);

			doReturn(Optional.of(user)).when(userRepository).findById(id);

			// act
			userService.updateUserById(id, updateUser);

			// assert
			verify(userRepository, times(1)).findById(id);
			verify(userRepository, times(1)).save(userArgumentCaptor.capture());
			
			
			assertEquals(updateUser.getUsername(), user.getUsername());
			assertEquals(updateUser.getPassword(), user.getPassword());
			assertNotNull(user.getUpdateTimestamp());

		}

		@Test
		@DisplayName("Should not update user when user not exists")
		void shouldNotUpdateUserWhenUserNotExists() {

			// Arrange
			Long id = 1L;
			UserRequestDTO dto = new UserRequestDTO("newUsername", "newemail@email.com", "newPassword");
			User user = new User(dto);

			doThrow(new ControllerNotFoundException(id)).when(userRepository).findById(id);

			// Act & Assert
			assertThrows(ControllerNotFoundException.class, () -> userService.updateUserById(id, user));

			verify(userRepository).findById(id);
			verify(userRepository, never()).save(any());

		}
	}

}

package lk.test.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import lk.test.data.entities.User;
import lk.test.repositories.UserRepo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
class UserService_UT {

	@Autowired
	UserService userService;

	@MockBean
	UserRepo userReopsitory;

	@Test
	public void getUserByIdTest() {
		int userId = 5555;
		User u = new User(userId, "firstName", "lastName", "user@mail.com");
		Optional<User> users = Optional.of(u);

		when(userReopsitory.findById(userId)).thenReturn(users);
		assertEquals(u, userService.getUserById(userId));

		// for additional validation we can check user id also
		assertEquals(userId, userService.getUserById(userId).getUserId());

	}

	@Test
	public void addUserTest() {

		User u = new User(4444, "frName", "lsName", "usr@mail.com");
		when(userReopsitory.save(u)).thenReturn(u);
		assertEquals(u, userService.addUser(u));

	}

	@Test
	public void removeUserTest() {

		User deleteUser = new User(11111, "dUser", "dU", "du@mail.com");
		userService.removeUser(deleteUser);
		verify(userReopsitory, times(1)).delete(deleteUser);
	}

	@Test
	public void findAllUsersTest() {
		User user1 = new User(7890, "aaaa", "bbbb", "ab@mai.com");
		User user2 = new User(8890, "acca", "bccb", "ac@mai.com");
		User user3 = new User(9890, "adda", "bddb", "ad@mai.com");

		when(userReopsitory.findAll()).thenReturn(Stream.of(user1, user2, user3).collect(Collectors.toList()));
		assertEquals(3, userService.findAllUsers().size());

	}

	@Test
	public void updateTest() {

		User updatedUser = new User(1234, "up", "updat", "pd@gmail.com");
		when(userReopsitory.save(updatedUser)).thenReturn(updatedUser);
		assertEquals(updatedUser, userService.updateUser(updatedUser));
	}

}

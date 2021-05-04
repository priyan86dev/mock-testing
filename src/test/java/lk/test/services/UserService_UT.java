package lk.test.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import lk.test.data.entities.User;
import lk.test.repositories.UserRepo;

@RunWith(MockitoJUnitRunner.class)
public class UserService_UT {

	@InjectMocks
	UserService userService;

	@Mock
	UserRepo userReopsitory;

	@Test
	public void getUserById() {

		User u = new User(3, "uf", "ul", "uful@mail.com");
		userReopsitory.save(u);

		User user = userService.gerUserById(3);
		System.out.println("User :" + user.toString());

		assertEquals("uf", user.getFirstName());

	}

}

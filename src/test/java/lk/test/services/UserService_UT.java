package lk.test.services;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import lk.test.data.entities.User;
import lk.test.repositories.UserRepo;

//@RunWith(MockitoJUnitRunner.class)
class UserService_UT {

	UserService userService;

	@Mock
	UserRepo userReopsitory;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		userService = new UserService(userReopsitory);
	}

	@Test
	public void getUserById() {
//		User u = new User(4, "uf", "ul", "uful@mail.com");
//		userService.addUser(u);

		User user = userService.gerUserById(1);
		assertNotNull(user);

//		when(userReopsitory.findById(2).get()).thenReturn(new User(2, "q", "qq", "qq@gmail.com"));

//		User user = userService.gerUserById(3);
//		System.out.println("User :" + user.toString());
//
//		assertEquals("uf", user.getFirstName());

	}

}

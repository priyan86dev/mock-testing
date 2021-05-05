package lk.test.services;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import lk.test.data.entities.User;
import lk.test.repositories.UserRepo;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
class UserService_UT {

	@Autowired
	@InjectMocks
	UserService userService;

	@Mock
	UserRepo userReopsitory;

	@Before
	public void init() {
//		MockitoAnnotations.initMocks(this);
		userService = new UserService(userReopsitory);
	}
	
	@Test
	public void testUserExists() {
		// Check whether particular user exists.
		User user = userService.gerUserById(1);
		assertNotNull(user);
		
	}

	@Test
	public void getUserById() {

//		User u = new User(4, "uf", "ul", "uful@mail.com");
//
//		when(userReopsitory.save( any())).thenReturn(u);
//		userService.addUser(u);
//		verify(userReopsitory, times(1)).save((User) any(User.class));


//		when(userReopsitory.findById(4).get()).thenReturn(new User(2, "q", "qq", "qq@gmail.com"));

//		User user = userService.gerUserById(3);
//		System.out.println("User :" + user.toString());
//
//		assertEquals("uf", user.getFirstName());

	}

}

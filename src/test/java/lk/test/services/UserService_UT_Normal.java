package lk.test.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lk.test.data.entities.User;
import lk.test.repositories.UserRepo;

@SpringBootTest
class UserService_UT_Normal {
	@Autowired
	private UserRepo userRepository;

	private UserService userService;

	@BeforeEach
	void setUp() throws Exception {
		userService = new UserService(userRepository);
	}

	@Test
	void test() {
		User testUser = new User(3, "tUser1", "tU", "tuser@mail.com");

		User savedUser = userService.addUser(testUser);
		assertEquals(3, savedUser.getUserId());
	}

}

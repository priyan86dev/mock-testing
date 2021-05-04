package lk.test;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lk.test.data.entities.User;
import lk.test.services.UserService;

@Component
public class BootstrapData implements CommandLineRunner {

	private UserService userService;

	public BootstrapData(UserService userService) {
		this.userService = userService;
	}

	@Override
	public void run(String... args) throws Exception {

		User user1 = new User("p", "pp", "pp@gmail.com");
		User user2 = new User("q", "qq", "qq@gmail.com");
		userService.addUser(user1);
		userService.addUser(user2);
	}

}

package lk.test.controllers.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lk.test.data.entities.User;
import lk.test.services.UserService;

@RestController
@RequestMapping("/user")
public class UserAPIController {

	private UserService userService;

	public UserAPIController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/{uId}")
	public User getUser(@PathVariable(value = "/uId") int userId) {
		return userService.gerUserById(userId);
	}

}

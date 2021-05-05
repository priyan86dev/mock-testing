package lk.test.controllers.rest;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	public User getUser(@PathVariable(value = "uId") int userId) {
		return userService.getUserById(userId);
	}

	@PostMapping("/save")
	public void saveUser(@RequestBody User user) {
		userService.addUser(user);
	}

	@GetMapping("/all")
	public List<User> getAllUsers() {
		return userService.findAllUsers();
	}

	@DeleteMapping("/delete")
	public void deleteUser(@RequestBody User user) {
		userService.removeUser(user);
	}

	@PutMapping("/update")
	public void updateUser(@RequestBody User user) {		
		userService.updateUser(user);
	}
}

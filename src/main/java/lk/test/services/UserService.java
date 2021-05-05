package lk.test.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import lk.test.data.entities.User;
import lk.test.repositories.UserRepo;

@Service
public class UserService {

	private UserRepo userRepository;

	public UserService(UserRepo userRepository) {
		this.userRepository = userRepository;
	}

	public User getUserById(int userId) {
		return userRepository.findById(userId).get();
	}

	public User addUser(User user) {
		return userRepository.save(user);
	}

	public void removeUser(User user) {
//		userRepository.deleteById(userId);
		userRepository.delete(user);
	}

	public List<User> findAllUsers() {
		List<User> userList = new ArrayList<>();
		userRepository.findAll().forEach(userList::add);
		return userList;
	}

	public User updateUser(User user) {
		return userRepository.save(user);
	}

}

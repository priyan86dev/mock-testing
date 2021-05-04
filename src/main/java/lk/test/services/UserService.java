package lk.test.services;

import org.springframework.stereotype.Service;

import lk.test.data.entities.User;
import lk.test.repositories.UserRepo;

@Service
public class UserService {

	private UserRepo userRepository;

	public UserService(UserRepo userRepository) {
		this.userRepository = userRepository;
	}

	public User gerUserById(int userId) {
		return userRepository.findById(userId).get();
	}

	public User addUser(User user) {
		return userRepository.save(user);
	}

}

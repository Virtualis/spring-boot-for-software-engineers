package ua.bohomolov.learningspringboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.bohomolov.learningspringboot.dao.UserDao;
import ua.bohomolov.learningspringboot.model.User;
import ua.bohomolov.learningspringboot.model.User.Gender;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

	private UserDao userDao;

	@Autowired
	public UserService(UserDao userDao) {
		this.userDao = userDao;
	}

	public List<User> getAllUsers(Optional<String> optionalGender) {
		List<User> users = userDao.selectAllUsers();
		if (!optionalGender.isPresent()) {
			return users;
		}

		try {
			Gender gender = Gender.valueOf(optionalGender.get().toUpperCase());
			return users.stream()
					.filter(user -> user.getGender().equals(gender))
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new IllegalArgumentException("Invalid gender [" + optionalGender.get() + "]", e);
		}
	}

	public Optional<User> getUser(UUID userUid) {
		return userDao.selectUserByUserUid(userUid);
	}

	public int updateUser(User user) {
		Optional<User> optionalUser = getUser(user.getUserUid());
		if (optionalUser.isPresent()) {
			return userDao.updateUser(user);
		}
		return -1;
	}

	public int removeUser(UUID userUid) {
		Optional<User> optionalUser = getUser(userUid);
		if (optionalUser.isPresent()) {
			return userDao.deleteUserByUserUid(userUid);
		}
		return -1;
	}

	public int insertUser(User user) {
		UUID userUid = UUID.randomUUID();
		user.setUserUid(userUid);
		return userDao.insertUser(userUid, user);
	}
}

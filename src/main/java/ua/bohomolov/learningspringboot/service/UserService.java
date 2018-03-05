package ua.bohomolov.learningspringboot.service;

import ua.bohomolov.learningspringboot.model.User;

import java.util.List;
import java.util.UUID;

public class UserService {

    public List<User> getAllUsers() {
        return null;
    }

    public User getUser(UUID userUid) {
        return null;
    }

    public int updateUser(User user) {
        return 1;
    }

    public int removeUser(UUID userUid) {
        return 1;
    }

    public UserService() {
        super();
    }

    public int insertUser(User user) {
        return 1;
    }
}

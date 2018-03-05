package ua.bohomolov.learningspringboot.dao;

import ua.bohomolov.learningspringboot.model.User;

import java.util.List;
import java.util.UUID;

public interface UserDao {

    List<User> getAllUsers();

    User getUser(UUID userUid);

    int updateUser(UUID userUid);

    int deleteUser(UUID userUid);

    int insertUser(User user);

}

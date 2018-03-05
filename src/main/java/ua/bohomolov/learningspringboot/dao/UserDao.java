package ua.bohomolov.learningspringboot.dao;

import ua.bohomolov.learningspringboot.model.User;

import java.util.List;
import java.util.UUID;

public interface UserDao {

    List<User> selectAllUsers();

    User selectUserByUserUid(UUID userUid);

    int updateUser(User user);

    int deleteUserByUserUid(UUID userUid);

    int insertUser(User user);

}

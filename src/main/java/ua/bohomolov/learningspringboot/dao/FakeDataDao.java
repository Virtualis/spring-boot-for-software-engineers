package ua.bohomolov.learningspringboot.dao;

import ua.bohomolov.learningspringboot.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class FakeDataDao implements UserDao {

    private static Map<UUID, User> database;

    static {
        database = new HashMap<>();
        UUID joeUserUid = UUID.randomUUID();
        database.put(joeUserUid, new User(joeUserUid, "Joe", "Jones", User.Gender.MALE, 22, "joe.jones@gmail.com"));
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public User getUser(UUID userUid) {
        return null;
    }

    @Override
    public int updateUser(UUID userUid) {
        return 0;
    }

    @Override
    public int deleteUser(UUID userUid) {
        return 0;
    }

    @Override
    public int insertUser(User user) {
        return 0;
    }
}

package ua.bohomolov.learningspringboot.dao;

import org.springframework.stereotype.Repository;
import ua.bohomolov.learningspringboot.model.User;

import java.util.*;

@Repository
public class FakeDataDao implements UserDao {

    private static Map<UUID, User> database;

    static {
        database = new HashMap();
        UUID joeUserUid = UUID.randomUUID();
        database.put(joeUserUid, new User(joeUserUid, "Joe", "Jones", User.Gender.MALE, 22, "joe.jones@gmail.com"));
    }

    @Override
    public List<User> selectAllUsers() {
        return new ArrayList(database.values());
    }

    @Override
    public User selectUserByUserUid(UUID userUid) {
        return database.get(userUid);
    }

    @Override
    public int updateUser(User user) {
        database.put(user.getUserUid(), user);
        return 1;
    }

    @Override
    public int deleteUserByUserUid(UUID userUid) {
        database.remove(userUid);
        return 1;
    }

    @Override
    public int insertUser(User user) {
        database.put(user.getUserUid(), user);
        return 1;
    }
}

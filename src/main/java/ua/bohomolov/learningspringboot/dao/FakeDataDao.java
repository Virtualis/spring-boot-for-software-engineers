package ua.bohomolov.learningspringboot.dao;

import org.springframework.stereotype.Repository;
import ua.bohomolov.learningspringboot.model.User;

import java.util.*;

@Repository
public class FakeDataDao implements UserDao {

    private Map<UUID, User> database;

    public FakeDataDao() {
        this.database = new HashMap<>();
        UUID joeUserUid = UUID.randomUUID();
        database.put(joeUserUid, new User(joeUserUid, "Joe", "Jones", User.Gender.MALE, 22, "joe.jones@gmail.com"));
        UUID annaUserUid = UUID.randomUUID();
        database.put(annaUserUid, new User(annaUserUid, "Anna", "Karenina", User.Gender.FEMALE, 19, "anna.karenina@gmail.com"));
    }

    @Override
    public List<User> selectAllUsers() {
        return new ArrayList<>(database.values());
    }

    @Override
    public Optional<User> selectUserByUserUid(UUID userUid) {
        return Optional.ofNullable(database.get(userUid));
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
    public int insertUser(UUID userUid, User user) {
        database.put(userUid, user);
        return 1;
    }
}

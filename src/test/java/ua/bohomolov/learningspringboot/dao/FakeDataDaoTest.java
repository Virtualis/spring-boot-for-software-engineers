package ua.bohomolov.learningspringboot.dao;

import org.junit.Before;
import org.junit.Test;
import ua.bohomolov.learningspringboot.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class FakeDataDaoTest {

    private FakeDataDao fakeDataDao;

    @Before
    public void setUp() throws Exception {
        fakeDataDao = new FakeDataDao();
    }

    @Test
    public void shouldSelectAllUsers() throws Exception {
        List<User> users = fakeDataDao.selectAllUsers();
        assertThat(users).hasSize(1);

        User user = users.get(0);
        assertThat(user.getAge()).isEqualTo(22);
        assertThat(user.getFirstName()).isEqualTo("Joe");
        assertThat(user.getLastName()).isEqualTo("Jones");
        assertThat(user.getUserUid()).isNotNull();
    }

    @Test
    public void shouldSelectUserByUserUid() throws Exception {
        UUID annaUserUid = UUID.randomUUID();
        User annaUser = new User(annaUserUid, "anna", "montana", User.Gender.FEMALE, 30, "anna@gmail.com");
        fakeDataDao.insertUser(annaUserUid, annaUser);
        assertThat(fakeDataDao.selectAllUsers()).hasSize(2);

        Optional<User> optionalUser = fakeDataDao.selectUserByUserUid(annaUserUid);
        assertThat(optionalUser.isPresent()).isTrue();
        assertThat(optionalUser.get()).isEqualToComparingFieldByField(annaUser);
    }

    @Test
    public void shouldNotSelectUserByRandomUserUid() throws Exception {
        Optional<User> optionalUser = fakeDataDao.selectUserByUserUid(UUID.randomUUID());
        assertThat(optionalUser.isPresent()).isFalse();
    }

    @Test
    public void updateUser() throws Exception {

    }

    @Test
    public void deleteUserByUserUid() throws Exception {

    }

    @Test
    public void insertUser() throws Exception {

    }

}
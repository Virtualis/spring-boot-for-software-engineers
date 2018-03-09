package ua.bohomolov.learningspringboot.service;

import com.google.common.collect.ImmutableList;
import com.sun.javafx.collections.ImmutableObservableList;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.bohomolov.learningspringboot.dao.FakeDataDao;
import ua.bohomolov.learningspringboot.model.User;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

public class UserServiceTest {

    @Mock
    private FakeDataDao fakeDataDao;

    private UserService userService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        userService = new UserService(fakeDataDao);
    }

    @Test
    public void shouldGetAllUsers() throws Exception {
        UUID annaUserUid = UUID.randomUUID();
        User annaUser = new User(annaUserUid, "anna", "montana", User.Gender.FEMALE, 30, "anna@gmail.com");
        ImmutableList<User> users = new ImmutableList.Builder<User>().add(annaUser).build();

        given(fakeDataDao.selectAllUsers()).willReturn(users);

        List<User> allUsers = userService.getAllUsers();
        assertThat(allUsers).hasSize(1);

        User user = allUsers.get(0);
        assertThat(user.getUserUid()).isEqualTo(annaUserUid);
    }

    @Test
    public void getUser() throws Exception {

    }

    @Test
    public void updateUser() throws Exception {

    }

    @Test
    public void removeUser() throws Exception {

    }

    @Test
    public void insertUser() throws Exception {

    }

}
package ua.bohomolov.learningspringboot.service;

import com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.bohomolov.learningspringboot.dao.FakeDataDao;
import ua.bohomolov.learningspringboot.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

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

		List<User> allUsers = userService.getAllUsers(Optional.empty());
		assertThat(allUsers).hasSize(1);

		User user = allUsers.get(0);
		assertThat(user.getUserUid()).isEqualTo(annaUserUid);
	}

	@Test
	public void shouldGetAllUsersByGender() {
		UUID joeUserUid = UUID.randomUUID();
		User joeUser = new User(joeUserUid, "Joe", "Jones", User.Gender.MALE, 30, "joe.jones@gmail.com");
		UUID annaUserUid = UUID.randomUUID();
		User annaUser = new User(annaUserUid, "Anna", "Montana", User.Gender.FEMALE, 30, "anna.montana@gmail.com");

		ImmutableList<User> users = new ImmutableList.Builder<User>().add(joeUser, annaUser).build();

		given(fakeDataDao.selectAllUsers()).willReturn(users);

		List<User> filteredUsers = userService.getAllUsers(Optional.of("female"));
		assertThat(filteredUsers).hasSize(1);

		User user = filteredUsers.get(0);
		assertThat(user.getUserUid()).isEqualTo(annaUserUid);
	}

	@Test
	public void shouldThrowExceptionWhenGenderInvalid() {
		assertThatThrownBy(() -> userService.getAllUsers(Optional.of("invalid_gender")))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("Invalid gender");
	}

	@Test
	public void shouldGetUser() throws Exception {
		UUID annaUid = UUID.randomUUID();
		User annaUser = new User(annaUid, "anna", "montana", User.Gender.FEMALE, 30, "anna@gmail.com");

		given(fakeDataDao.selectUserByUserUid(annaUid)).willReturn(Optional.of(annaUser));

		Optional<User> userOptional = userService.getUser(annaUid);
		assertThat(userOptional.isPresent()).isTrue();

		User user = userOptional.get();
		assertThat(user.getUserUid()).isEqualTo(annaUid);
	}

	@Test
	public void shouldUpdateUser() throws Exception {
		UUID annaUid = UUID.randomUUID();
		User annaUser = new User(annaUid, "anna", "montana", User.Gender.FEMALE, 30, "anna@gmail.com");

		given(fakeDataDao.selectUserByUserUid(annaUid)).willReturn(Optional.of(annaUser));
		given(fakeDataDao.updateUser(annaUser)).willReturn(1);

		ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);

		int result = userService.updateUser(annaUser);
		assertThat(result).isEqualTo(1);

		verify(fakeDataDao).selectUserByUserUid(annaUid);
		verify(fakeDataDao).updateUser(captor.capture());

		User user = captor.getValue();
		assertThat(user.getUserUid()).isEqualTo(annaUid);
	}

	@Test
	public void shouldRemoveUser() throws Exception {
		UUID annaUid = UUID.randomUUID();
		User annaUser = new User(annaUid, "anna", "montana", User.Gender.FEMALE, 30, "anna@gmail.com");

		given(fakeDataDao.selectUserByUserUid(annaUid)).willReturn(Optional.of(annaUser));
		given(fakeDataDao.deleteUserByUserUid(annaUid)).willReturn(1);

		int result = userService.removeUser(annaUid);
		assertThat(result).isEqualTo(1);

		verify(fakeDataDao).selectUserByUserUid(annaUid);
		verify(fakeDataDao).deleteUserByUserUid(annaUid);
	}

	@Test
	public void shouldInsertUser() throws Exception {
		User annaUser = new User(null, "anna", "montana", User.Gender.FEMALE, 30, "anna@gmail.com");

		given(fakeDataDao.insertUser(any(UUID.class), eq(annaUser))).willReturn(1);

		ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);

		int result = userService.insertUser(annaUser);
		assertThat(result).isEqualTo(1);

		verify(fakeDataDao).insertUser(any(UUID.class), captor.capture());
		User user = captor.getValue();
		assertThat(user).isEqualTo(annaUser);
		assertThat(user.getUserUid()).isNotNull();
	}

}
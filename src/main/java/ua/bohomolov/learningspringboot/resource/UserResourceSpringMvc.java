package ua.bohomolov.learningspringboot.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.bohomolov.learningspringboot.model.User;
import ua.bohomolov.learningspringboot.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.ws.rs.QueryParam;

//@RestController
//@RequestMapping(
//		path = "/api/v1/users"
//)
public class UserResourceSpringMvc {

	private UserService userService;

	@Autowired
	public UserResourceSpringMvc(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public List<User> fetchUsers(@QueryParam("gender") String gender) {
		Optional<String> optionalGender = Optional.ofNullable(gender);
		return userService.getAllUsers(optionalGender);
	}

	@RequestMapping(
			path = "{userUid}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<?> fetchUser(@PathVariable(name = "userUid") UUID userUid) {
		Optional<User> userOptional = userService.getUser(userUid);
		if (!userOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ErrorMessage("User " + userUid + " was not found"));
		}

		return ResponseEntity.ok(userOptional.get());
	}

	@RequestMapping(
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<Integer> insertUser(@RequestBody User user) {
		int result = userService.insertUser(user);
		return getIntegerResponseEntity(result);
	}

	@RequestMapping(
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<Integer> updateUser(@RequestBody User user) {
		int result = userService.updateUser(user);
		return getIntegerResponseEntity(result);
	}

	@RequestMapping(
			path = "{userUid}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<Integer> deleteUser(@PathVariable("userUid") UUID userUid) {
		int result = userService.removeUser(userUid);
		return getIntegerResponseEntity(result);
	}

	private ResponseEntity<Integer> getIntegerResponseEntity(int result) {
		if (result == 1) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	class ErrorMessage {

		private String errorMessage;

		public ErrorMessage(String errorMessage) {
			this.errorMessage = errorMessage;
		}

		public String getErrorMessage() {
			return errorMessage;
		}

		public void setErrorMessage(String errorMessage) {
			this.errorMessage = errorMessage;
		}
	}
}

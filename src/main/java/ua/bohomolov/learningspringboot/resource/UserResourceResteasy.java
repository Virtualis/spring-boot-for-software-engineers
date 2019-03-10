package ua.bohomolov.learningspringboot.resource;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ua.bohomolov.learningspringboot.model.User;
import ua.bohomolov.learningspringboot.service.UserService;

@Component
@Path("api/v1/users")
public class UserResourceResteasy {
	
	private UserService userService;
	
	@Autowired
	public UserResourceResteasy(UserService userService) {
		this.userService = userService;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> fetchUsers(@QueryParam("gender") String gender) {
		Optional<String> optionalGender = Optional.ofNullable(gender);
		return userService.getAllUsers(optionalGender);
	}

}

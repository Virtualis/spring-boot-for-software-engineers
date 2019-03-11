package ua.bohomolov.learningspringboot.resource;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

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

	@Path("{userUid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response fetchUser(@PathParam("userUid") UUID userUid) {
		return userService.getUser(userUid)
			.map(user -> Response.ok(user).build())
			.orElse(Response.status(Status.NOT_FOUND).entity(new ErrorMessage("User " + userUid + " was not found")).build());
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertUser(User user) {
		int result = userService.insertUser(user);
		return getIntegerResponseEntity(result);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUser(User user) {
		int result = userService.updateUser(user);
		return getIntegerResponseEntity(result);
	}

	@Path("{userUid}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteUser(@PathParam("userUid") UUID userUid) {
		int result = userService.removeUser(userUid);
		return getIntegerResponseEntity(result);
	}

	private Response getIntegerResponseEntity(int result) {
		if (result == 1) {
			return Response.ok().build();
		} else {
			return Response.status(Status.BAD_REQUEST).build();
		}
	}

}

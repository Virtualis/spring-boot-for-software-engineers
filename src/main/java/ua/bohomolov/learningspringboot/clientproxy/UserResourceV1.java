package ua.bohomolov.learningspringboot.clientproxy;

import java.util.List;
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

import ua.bohomolov.learningspringboot.model.User;

public interface UserResourceV1 {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> fetchUsers(@QueryParam("gender") String gender);

	@Path("{userUid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response fetchUser(@PathParam("userUid") UUID userUid);

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertUser(User user);

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUser(User user);

	@Path("{userUid}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteUser(@PathParam("userUid") UUID userUid);

}

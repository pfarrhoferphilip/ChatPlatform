package at.pfarrhofer.user;

import at.pfarrhofer.security.PasswordUtil;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/users")
public class UserResource {

    @GET
    public Response getAllUsers() {
        List<User> users = User.listAll();
        return Response.ok(users.stream().map(UserDTO::toResource)).build();
    }

    @GET
    @Path("/{id}")
    public Response getUser(@PathParam("id") long id) {
        return Response.ok(UserDTO.toResource(User.findById(id))).build();
    }

    @PUT
    @Path("/login")
    public Response login(UserCreateDTO dto) {
        User user = User.find("username", dto.username()).singleResult();
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        if (!PasswordUtil.verifyPassword(dto.password(), user.password)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        return Response.ok(UserDTO.toResource(user)).build();
    }

    @POST
    @Path("/create")
    @Transactional
    public Response createUser(UserCreateDTO dto) {
        if (dto.username().isEmpty() || dto.password().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        User user = new User(dto.username(), PasswordUtil.hashPassword(dto.password()));
        User.persist(user);
        return Response.status(Response.Status.CREATED).entity(UserDTO.toResource(user)).build();
    }
}

package at.pfarrhofer.user;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
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

    @POST
    public Response createUser(UserDTO user) {
        
    }
}

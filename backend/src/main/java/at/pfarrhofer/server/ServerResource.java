package at.pfarrhofer.server;

import at.pfarrhofer.message.Message;
import at.pfarrhofer.message.MessageCreateDTO;
import at.pfarrhofer.message.MessageDTO;
import at.pfarrhofer.user.User;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/servers")
public class ServerResource {

    @GET
    public Response getAllServers() {
        List<Server> servers = Server.listAll();
        return Response.ok(servers.stream().map(ServerDTO::toResource)).build();
    }

    @GET
    @Path("/{id}/messages")
    public Response getAllMessages(@PathParam("id") long id) {
        List<Message> messages = Message.list("server.id", id);
        return Response.ok(messages.stream().map(MessageDTO::toResource)).build();
    }

    @POST
    @Path("/{id}/messages")
    @Transactional
    public Response addMessage(@PathParam("id") long id, MessageCreateDTO dto) {
        Message message = new Message(dto.content(), User.findById(dto.userId()), Server.findById(id));
        Message.persist(message);
        return Response.status(Response.Status.CREATED).entity(MessageDTO.toResource(message)).build();
    }
}

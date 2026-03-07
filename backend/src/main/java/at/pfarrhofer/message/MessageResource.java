package at.pfarrhofer.message;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/messages")
public class MessageResource {

    @GET
    public Response getAllMessages() {
        List<Message> messages = Message.listAll();
        return Response.ok(messages.stream().map(MessageDTO::toResource)).build();
    }
}

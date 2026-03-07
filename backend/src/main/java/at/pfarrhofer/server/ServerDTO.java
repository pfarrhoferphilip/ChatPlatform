package at.pfarrhofer.server;

import at.pfarrhofer.message.MessageDTO;
import at.pfarrhofer.user.UserDTO;

import java.util.List;

public record ServerDTO(
        long id,
        String name,
        List<MessageDTO> messages,
        List<UserDTO> users
) {
    public static ServerDTO toResource(Server server) {
        return new ServerDTO(server.id, server.name, server.messages.stream().map(MessageDTO::toResource).toList(),
                server.users.stream().map(UserDTO::toResource).toList());
    }
}

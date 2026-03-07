package at.pfarrhofer.message;

import at.pfarrhofer.user.UserDTO;

public record MessageDTO(
        long id,
        String content,
        UserDTO user
) {
    public static MessageDTO toResource(Message message) {
        return new MessageDTO(message.id, message.content, UserDTO.toResource(message.user));
    }
}

package at.pfarrhofer.message;

public record MessageCreateDTO(
        String content,
        long userId
) {
}

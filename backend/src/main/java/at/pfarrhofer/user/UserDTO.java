package at.pfarrhofer.user;

public record UserDTO(
        long id,
        String username
) {
    public static UserDTO toResource(User user) {
        return new UserDTO(user.id, user.username);
    }
}

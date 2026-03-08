package at.pfarrhofer.security;

import at.pfarrhofer.user.User;
import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.HttpHeaders;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext ctx) {

        String path = ctx.getUriInfo().getPath();

        // allow login and user creation
        if (path.startsWith("/users/login") || path.startsWith("/users/create")) {
            return;
        }

        String auth = ctx.getHeaderString(HttpHeaders.AUTHORIZATION);

        if (auth == null || !isValid(auth)) {
            ctx.abortWith(
                    Response.status(Response.Status.UNAUTHORIZED).build()
            );
        }
    }

    private boolean isValid(String authHeader) {
        String[] parts = authHeader.split(":");
        if (parts.length != 2) {
            return false;
        }
        User user = User.findById(parts[0]);
        if (user == null) {
            return false;
        }
        return PasswordUtil.verifyPassword(parts[1], user.password);
    }
}
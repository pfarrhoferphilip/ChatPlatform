package at.pfarrhofer.server;

import at.pfarrhofer.message.Message;
import at.pfarrhofer.message.MessageDTO;
import jakarta.enterprise.context.ApplicationScoped;
import io.vertx.core.json.Json;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/ws/servers")
@ApplicationScoped
public class ServerSocket {

    private Set<Session> sessions = new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
        //session.getAsyncRemote().sendText("Connected");
    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        sessions.remove(session);
        error.printStackTrace();
    }

    public void sendMessage(Message message) {
        for (Session session : sessions) {
            session.getAsyncRemote().sendText(Json.encode(MessageDTO.toResource(message)));
        }
    }
}

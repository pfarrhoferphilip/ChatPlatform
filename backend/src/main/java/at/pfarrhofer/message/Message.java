package at.pfarrhofer.message;

import at.pfarrhofer.server.Server;
import at.pfarrhofer.user.User;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Message extends PanacheEntity {
    public String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User user;

    @ManyToOne
    @JoinColumn(name = "server_id")
    public Server server;

    public Message() {}

    public Message(String content, User user, Server server) {
        this.content = content;
        this.user = user;
        this.server = server;
    }
}

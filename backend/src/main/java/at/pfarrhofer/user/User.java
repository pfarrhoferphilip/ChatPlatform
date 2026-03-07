package at.pfarrhofer.user;

import at.pfarrhofer.message.Message;
import at.pfarrhofer.server.Server;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "chat_user")
public class User extends PanacheEntity {
    public String username;

    @OneToMany(mappedBy = "user")
    public List<Message> messages;

    @ManyToMany(mappedBy = "users")
    public List<Server> servers;
}

package at.pfarrhofer.server;

import at.pfarrhofer.message.Message;
import at.pfarrhofer.user.User;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Server extends PanacheEntity {
    public String name;

    @OneToMany(mappedBy = "server")
    List<Message> messages;

    @ManyToMany
    @JoinTable(
            name = "server_user",
            joinColumns = @JoinColumn(name = "server_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    List<User> users;
}

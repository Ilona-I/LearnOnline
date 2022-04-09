package nure.ua.learn.online.entities;

import lombok.Getter;
import lombok.Setter;
import nure.ua.learn.online.entities.keys.ChatId;

import javax.persistence.*;

@Entity
@Table(name = "chat")
@IdClass(ChatId.class)
@Getter
@Setter
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Id
    @Column(name = "login")
    private String login;
}

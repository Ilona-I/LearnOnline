package nure.ua.learn.online.entities;

import lombok.Getter;
import lombok.Setter;
import nure.ua.learn.online.entities.keys.UserChatId;

import javax.persistence.*;

@Entity
@Table(name = "user_chat")
@IdClass(UserChatId.class)
@Getter
@Setter
public class UserChat {

    @Id
    @Column(name = "chat_id")
    private int chatId;
    @Id
    @Column(name = "login")
    private String login;
}

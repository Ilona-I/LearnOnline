package nure.ua.learn.online.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "message")
@Getter
@Setter
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "chat_id")
    private int chatId;
    @Column(name = "sender")
    private String sender;
    @Column(name = "text")
    private String text;
    @Column(name = "datetime")
    private Timestamp dateTime;
}

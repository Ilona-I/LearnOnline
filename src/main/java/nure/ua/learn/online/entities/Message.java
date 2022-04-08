package nure.ua.learn.online.entities;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class Message {

    private int id;
    private int chatId;
    private String senderLogin;
    private String text;
    private Timestamp dateTime;
}

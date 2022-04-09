package nure.ua.learn.online.entities.keys;

import java.io.Serializable;
import java.util.Objects;

public class ChatId implements Serializable {

    private int id;
    private String login;

    public ChatId() {
    }

    public ChatId(int id, String login) {
        this.id = id;
        this.login = login;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatId chatId = (ChatId) o;
        return id == chatId.id && Objects.equals(login, chatId.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login);
    }
}
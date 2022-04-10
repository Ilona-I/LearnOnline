package nure.ua.learn.online.entities.keys;

import java.io.Serializable;
import java.util.Objects;

public class UserChatId implements Serializable {

    private int chatId;
    private String login;

    public UserChatId() {
    }

    public UserChatId(int chatId, String login) {
        this.chatId = chatId;
        this.login = login;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserChatId that = (UserChatId) o;
        return chatId == that.chatId && Objects.equals(login, that.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chatId, login);
    }
}

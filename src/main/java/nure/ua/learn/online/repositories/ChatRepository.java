package nure.ua.learn.online.repositories;

import nure.ua.learn.online.entities.Chat;
import nure.ua.learn.online.entities.keys.ChatId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends JpaRepository <Chat, ChatId> {

}

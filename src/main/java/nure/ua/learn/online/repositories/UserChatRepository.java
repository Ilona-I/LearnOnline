package nure.ua.learn.online.repositories;

import nure.ua.learn.online.entities.UserChat;
import nure.ua.learn.online.entities.keys.UserChatId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserChatRepository extends JpaRepository<UserChat, UserChatId> {
}

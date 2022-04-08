package nure.ua.learn.online.services;

import lombok.AllArgsConstructor;
import nure.ua.learn.online.entities.Chat;
import nure.ua.learn.online.repositories.ChatRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;

    public List<Chat> getUserChats(String login) {
        return chatRepository.findAll().stream().filter(chat -> chat.getLogin().equals(login)).collect(Collectors.toList());
    }
}

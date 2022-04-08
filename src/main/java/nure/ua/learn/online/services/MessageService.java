package nure.ua.learn.online.services;

import lombok.AllArgsConstructor;
import nure.ua.learn.online.entities.Message;
import nure.ua.learn.online.repositories.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    public List<Message> getChatMessages(int chatId) {
        return messageRepository.findAll().stream().filter(message -> message.getChatId() == chatId).collect(Collectors.toList());
    }
}

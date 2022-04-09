package nure.ua.learn.online.services;

import lombok.AllArgsConstructor;
import nure.ua.learn.online.entities.Chat;
import nure.ua.learn.online.entities.keys.ChatId;
import nure.ua.learn.online.entities.Message;
import nure.ua.learn.online.repositories.ChatRepository;
import nure.ua.learn.online.repositories.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;
    private final MessageRepository messageRepository;

    public Map<Integer, List<String>> getUserChats(String login) {
        List<Chat> currentUserChats = chatRepository.findAll().stream().filter(chat -> chat.getLogin().equals(login)).collect(Collectors.toList());
        Map<Integer, List<String>> userChats = new HashMap<>();
        for (Chat currentChat : currentUserChats) {
            List<Chat> chats = chatRepository.findAll().stream().filter(chat -> chat.getId() == currentChat.getId()).collect(Collectors.toList());
            List<String> logins = new ArrayList<>();
            for (Chat chat : chats) {
                logins.add(chat.getLogin());
            }
            userChats.put(currentChat.getId(), logins);
        }
        return userChats;
    }

    public List<Message> getChatMessages(int chatId) {
        return messageRepository.findAll().stream().filter(message -> message.getChatId() == chatId).collect(Collectors.toList());
    }

    public Integer getPrivateChatId(String currentUser, String receiver) {
        List<Chat> currentUserChats = chatRepository.findAll()
                .stream().filter(chat -> chat.getLogin().equals(currentUser)).collect(Collectors.toList());
        for (Chat currentChat : currentUserChats) {
            ChatId chatId = new ChatId(currentChat.getId(), receiver);
            if (chatRepository.existsById(chatId)) {
                return currentChat.getId();
            }
        }
        Chat chat = new Chat();
        chat.setLogin(currentUser);
        chat = chatRepository.save(chat);
        chat.setLogin(receiver);
        chatRepository.save(chat);
        return chat.getId();
    }
}

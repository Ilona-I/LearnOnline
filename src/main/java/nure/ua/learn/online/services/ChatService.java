package nure.ua.learn.online.services;

import lombok.AllArgsConstructor;
import nure.ua.learn.online.entities.Chat;
import nure.ua.learn.online.entities.Message;
import nure.ua.learn.online.entities.UserChat;
import nure.ua.learn.online.entities.keys.UserChatId;
import nure.ua.learn.online.repositories.ChatRepository;
import nure.ua.learn.online.repositories.MessageRepository;
import nure.ua.learn.online.repositories.UserChatRepository;
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
    private final UserChatRepository userChatRepository;
    private final MessageRepository messageRepository;

    public Map<Integer, List<String>> getUserChats(String login) {
        List<UserChat> currentUserChats = userChatRepository.findAll().stream().filter(userChat -> userChat.getLogin().equals(login)).collect(Collectors.toList());
        Map<Integer, List<String>> userChats = new HashMap<>();
        for (UserChat userChat : currentUserChats) {
            List<UserChat> chats = userChatRepository.findAll().stream().filter(chat -> chat.getChatId() == userChat.getChatId()).collect(Collectors.toList());
            List<String> logins = new ArrayList<>();
            for (UserChat chat : chats) {
                logins.add(chat.getLogin());
            }
            userChats.put(userChat.getChatId(), logins);
        }
        return userChats;
    }

    public List<Message> getChatMessages(int chatId) {
        return messageRepository.findAll().stream().filter(message -> message.getChatId() == chatId).collect(Collectors.toList());
    }

    public Integer getPrivateChatId(String currentUser, String receiver) {
        List<UserChat> currentUserChats = userChatRepository.findAll()
                .stream().filter(chat -> chat.getLogin().equals(currentUser)).collect(Collectors.toList());
        for (UserChat currentChat : currentUserChats) {
            if (userChatRepository.existsById(new UserChatId(currentChat.getChatId(), receiver))) {
                return currentChat.getChatId();
            }
        }
        Chat chat = new Chat();
        chat = chatRepository.save(chat);
        UserChat userChat = new UserChat();
        userChat.setChatId(chat.getChatId());
        userChat.setLogin(currentUser);
        userChatRepository.save(userChat);
        userChat.setLogin(receiver);
        userChatRepository.save(userChat);
        return chat.getChatId();
    }
}

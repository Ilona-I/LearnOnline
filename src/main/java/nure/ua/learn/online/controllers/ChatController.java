package nure.ua.learn.online.controllers;

import lombok.AllArgsConstructor;
import nure.ua.learn.online.DataValidator;
import nure.ua.learn.online.entities.Message;
import nure.ua.learn.online.entities.User;
import nure.ua.learn.online.services.ChatService;
import nure.ua.learn.online.services.MessageService;
import nure.ua.learn.online.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class ChatController {

    private final ChatService chatService;
    private final MessageService messageService;
    private final UserService userService;
    private final DataValidator dataValidator;

    @GetMapping("/chat")
    public String openChat(@RequestParam(name="chatId", required = false) String chatId, @RequestParam(name="receiver", required = false) String receiver, HttpServletRequest request) {
        int id = 0;
        if (receiver != null) {
            if (userService.isUserExists(receiver)) {
                id = chatService.getPrivateChatId(((User) request.getSession().getAttribute("currentUser")).getLogin(), receiver);
            } else {
                return "error";
            }
        }
        if (dataValidator.isNumeric(chatId)) {
            id = Integer.parseInt(chatId);
        }
        List<Message> chatMessages = chatService.getChatMessages(id);
        request.getSession().setAttribute("chatMessages", chatMessages);
        return "chat";
    }

    @GetMapping("/chatList")
    public String getChats(HttpSession httpSession) {
        User currentUser = (User) httpSession.getAttribute("currentUser");
        Map<Integer, List<String>> userChats = chatService.getUserChats(currentUser.getLogin());
        httpSession.setAttribute("userChats", userChats);
        return "chatList";
    }

    @PostMapping("/sendMessage")
    public RedirectView sendMessage(HttpServletRequest request) {
        String chatId = request.getParameter("chatId");
        if(chatId.isEmpty()){
            chatId = Integer.toString(chatService.getPrivateChatId(((User) request.getSession().getAttribute("currentUser")).getLogin(), request.getParameter("receiver")));
        }
        String text = request.getParameter("text");
        String sender = ((User)request.getSession().getAttribute("currentUser")).getLogin();
        Timestamp dateTime = new Timestamp(new Date().getTime());
        Message message = new Message();
        message.setChatId(Integer.parseInt(chatId));
        message.setSender(sender);
        message.setText(text);
        message.setDateTime(dateTime);
        messageService.sendMessage(message);
        return new RedirectView("chat?chatId="+chatId);
    }
}

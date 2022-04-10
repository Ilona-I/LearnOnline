package nure.ua.learn.online.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LogoutController {

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("currentUser");
        session.removeAttribute("chatMessages");
        session.removeAttribute("userChats");
        session.removeAttribute("courseForEditing");
        session.removeAttribute("wrongLogin");
        session.removeAttribute("wrongPassword");
        session.removeAttribute("validationErrors");
        session.removeAttribute("signUpData");
        session.removeAttribute("userMark");
        session.removeAttribute("maxMark");
        return "logIn";
    }
}

package nure.ua.learn.online.controllers;

import lombok.AllArgsConstructor;
import nure.ua.learn.online.entities.User;
import nure.ua.learn.online.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class UserListController {

    private final UserService userService;

    @GetMapping("/userList")
    public String getUsers(@RequestParam(required = false, name = "teacher") String teacherLogin, HttpSession session) {
        if (teacherLogin == null || !userService.isUserExists(teacherLogin)) {
            session.setAttribute("allUsers", userService.getAllUsers());
        } else {
            List<User> userList = new ArrayList<>();
            userList.add(userService.getUser(teacherLogin).get());
            session.setAttribute("allUsers", userList);
        }
        return "userList";
    }
}

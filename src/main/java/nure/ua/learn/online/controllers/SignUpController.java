package nure.ua.learn.online.controllers;

import lombok.AllArgsConstructor;
import nure.ua.learn.online.DataValidator;
import nure.ua.learn.online.dto.LogInData;
import nure.ua.learn.online.dto.SignUpData;
import nure.ua.learn.online.entities.User;
import nure.ua.learn.online.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@AllArgsConstructor
public class SignUpController {

    private final UserService userService;

    @GetMapping("/signUp")
    public String getLogin() {
        return "signUp";
    }

    @PostMapping("/signUp")
    public RedirectView login(HttpServletRequest request) {
        SignUpData signUpData = new SignUpData(request);
        if (userService.isUserExists(signUpData.getLogin())) {
            request.getSession().setAttribute("signUpData", signUpData);
            request.getSession().setAttribute("wrongLogin", "A user with this login already exists!");
            return new RedirectView("signUp");
        }
        Map<String, String> validationErrors = DataValidator.validateData(signUpData);
        if (!validationErrors.isEmpty()) {
            request.getSession().setAttribute("signUpData", signUpData);
            request.getSession().setAttribute("validationErrors", validationErrors);
            return new RedirectView("signUp");
        }
        User user = new User();
        user.setLogin(signUpData.getLogin());
        user.setPassword(signUpData.getPassword());
        user.setFirstName(signUpData.getFirstName());
        user.setLastName(signUpData.getLastName());
        user.setEmail(signUpData.getEmail());
        user.setRole(signUpData.getRole());
        userService.addNewUser(user);
        request.getSession().setAttribute("currentUser", user);
        return new RedirectView("index");
    }
}

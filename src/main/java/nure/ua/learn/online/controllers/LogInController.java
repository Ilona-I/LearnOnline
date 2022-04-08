package nure.ua.learn.online.controllers;

import lombok.AllArgsConstructor;
import nure.ua.learn.online.dto.LogInData;
import nure.ua.learn.online.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Controller
@AllArgsConstructor
public class LogInController {

    private final UserService userService;

    @GetMapping("/logIn")
    public String getLogin() {
        return "logIn";
    }

    @GetMapping("/index")
    public String getMain() {
        return "index";
    }

    @PostMapping("/logIn")
    public RedirectView login(HttpServletRequest request){
        LogInData logInData = new LogInData(request);
        if(!userService.isUserExists(logInData.getLogin())){
            request.getSession().setAttribute("wrongLogin", "There is no user with this login!");
            return new RedirectView("logIn");
        }
        if(!userService.checkPassword(logInData.getLogin(), logInData.getPassword())){
            request.getSession().setAttribute("wrongPassword", "Wrong password!");
            return new RedirectView("logIn");
        }
        request.getSession().setAttribute("currentUser", userService.getUser(logInData.getLogin()).get());
        return new RedirectView("index");
    }
}

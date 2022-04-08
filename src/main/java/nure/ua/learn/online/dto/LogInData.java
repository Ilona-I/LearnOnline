package nure.ua.learn.online.dto;

import lombok.Getter;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Getter
public class LogInData {

    private final String login;
    private final String password;

    public LogInData(HttpServletRequest request){
        login = Objects.requireNonNull(request.getParameter("login"));
        password = Objects.requireNonNull(request.getParameter("password"));
    }
}

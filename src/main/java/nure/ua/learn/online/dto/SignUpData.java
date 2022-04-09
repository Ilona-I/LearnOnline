package nure.ua.learn.online.dto;

import lombok.Getter;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Getter
public class SignUpData {

    private final String login;
    private final String password;
    private final String repeatedPassword;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String role;

    public SignUpData(HttpServletRequest request) {
        login = Objects.requireNonNull(request.getParameter("login"));
        password = Objects.requireNonNull(request.getParameter("password"));
        repeatedPassword = Objects.requireNonNull(request.getParameter("repeatedPassword"));
        firstName = Objects.requireNonNull(request.getParameter("firstName"));
        lastName = Objects.requireNonNull(request.getParameter("lastName"));
        email = Objects.requireNonNull(request.getParameter("email"));
        role = Objects.requireNonNull(request.getParameter("role"));
    }
}

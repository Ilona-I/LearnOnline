package nure.ua.learn.online.dto;

import lombok.Getter;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Getter
public class ProfileData {

    private final String firstName;
    private final String lastName;
    private final String email;

    public ProfileData(HttpServletRequest request) {
        firstName = Objects.requireNonNull(request.getParameter("firstName"));
        lastName = Objects.requireNonNull(request.getParameter("lastName"));
        email = Objects.requireNonNull(request.getParameter("email"));
    }
}

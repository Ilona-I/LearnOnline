package nure.ua.learn.online.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

    private String login;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
}

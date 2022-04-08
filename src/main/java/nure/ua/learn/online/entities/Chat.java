package nure.ua.learn.online.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Chat {

    private int id;
    private String userLogin;
}

package nure.ua.learn.online.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserCourse {

    private int courseId;
    private String userLogin;
    private double highestMark;
}

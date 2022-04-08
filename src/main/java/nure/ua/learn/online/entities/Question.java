package nure.ua.learn.online.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Question {

    private int id;
    private int courseId;
    private String text;
}

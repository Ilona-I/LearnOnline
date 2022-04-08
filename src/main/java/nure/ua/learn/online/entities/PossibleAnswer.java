package nure.ua.learn.online.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PossibleAnswer {

    private int id;
    private int questionId;
    private String text;
    private boolean isCorrect;
}

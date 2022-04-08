package nure.ua.learn.online.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "possible_answer")
@Getter
@Setter
public class PossibleAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "question_id")
    private int questionId;
    @Column(name = "text")
    private String text;
    @Column(name = "is_true")
    private boolean isTrue;
}

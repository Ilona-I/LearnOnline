package nure.ua.learn.online.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user_course")
@Getter
@Setter
public class UserCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int courseId;
    @Column(name = "login")
    private String login;
    @Column(name = "highest_mark")
    private double highestMark;
}

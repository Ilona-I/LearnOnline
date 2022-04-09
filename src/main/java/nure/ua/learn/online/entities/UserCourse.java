package nure.ua.learn.online.entities;

import lombok.Getter;
import lombok.Setter;
import nure.ua.learn.online.entities.keys.UserCourseId;

import javax.persistence.*;

@Entity
@Table(name = "user_course")
@IdClass(UserCourseId.class)
@Getter
@Setter
public class UserCourse {

    @Id
    @Column(name = "course_id")
    private Integer courseId;
    @Id
    @Column(name = "login")
    private String login;
    @Column(name = "highest_mark")
    private double highestMark;
}

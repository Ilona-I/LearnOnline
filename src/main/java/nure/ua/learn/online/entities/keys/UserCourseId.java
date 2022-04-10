package nure.ua.learn.online.entities.keys;

import java.io.Serializable;
import java.util.Objects;

public class UserCourseId implements Serializable {

    private Integer courseId;
    private String login;

    public UserCourseId() {
    }

    public UserCourseId(int courseId, String login) {
        this.courseId = courseId;
        this.login = login;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCourseId that = (UserCourseId) o;
        return Objects.equals(courseId, that.courseId) && Objects.equals(login, that.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, login);
    }
}

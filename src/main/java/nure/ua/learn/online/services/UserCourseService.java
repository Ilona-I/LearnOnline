package nure.ua.learn.online.services;

import lombok.AllArgsConstructor;
import nure.ua.learn.online.entities.Course;
import nure.ua.learn.online.entities.UserCourse;
import nure.ua.learn.online.repositories.CourseRepository;
import nure.ua.learn.online.repositories.UserCourseRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserCourseService {

    private final UserCourseRepository userCourseRepository;
    private final CourseRepository courseRepository;

    public List<Course> getUserCourses(String login) {
        List<Course> userCourses = new ArrayList<>();
        List<Course> allCourses = courseRepository.findAll();
        for (Course course : allCourses) {
            UserCourse userCourse = userCourseRepository.getById(course.getId());
            if (userCourse.getLogin().equals(login)) {
                userCourses.add(course);
            }
        }
        return userCourses;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public void createCourse(String login, Course course) {
        UserCourse userCourse = new UserCourse();
        userCourse.setCourseId(course.getId());
        userCourse.setLogin(login);
        userCourseRepository.save(userCourse);
        courseRepository.save(course);
    }
}

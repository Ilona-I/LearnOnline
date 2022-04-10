package nure.ua.learn.online.services;

import lombok.AllArgsConstructor;
import nure.ua.learn.online.entities.Course;
import nure.ua.learn.online.entities.User;
import nure.ua.learn.online.entities.UserCourse;
import nure.ua.learn.online.repositories.CourseRepository;
import nure.ua.learn.online.repositories.UserCourseRepository;
import nure.ua.learn.online.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserCourseService {

    private final UserCourseRepository userCourseRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public List<Course> getUserCourses(String login) {
        List<Course> userCourses = new ArrayList<>();
        List<Course> allCourses = courseRepository.findAll();
        for (Course course : allCourses) {
            List<UserCourse> userCourseList = userCourseRepository.findAll()
                    .stream().filter(userCourse1 -> userCourse1.getCourseId().equals(course.getId())).collect(Collectors.toList());
            for (UserCourse userCourse : userCourseList) {
                if (userCourse.getLogin().equals(login)) {
                    userCourses.add(course);
                }
            }
        }
        return userCourses;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Map<User, List<Course>> getCourses() {
        List<User> teachers = userRepository.findAll()
                .stream().filter(user -> user.getRole().equals("teacher")).collect(Collectors.toList());
        Map<User, List<Course>> userCoursesMap = new HashMap<>();
        for (User teacher : teachers) {
            List<Course> courses = getUserCourses(teacher.getLogin());
            if (!courses.isEmpty()) {
                userCoursesMap.put(teacher, courses);
            }
        }
        return userCoursesMap;
    }

    public Course getCourseById(int id) {
        return courseRepository.getById(id);
    }

    public User getCourseTeacher(Integer courseId) {
        Optional<UserCourse> optionalUserCourse = userCourseRepository.findAll()
                .stream().filter(userCourse -> courseId.equals(userCourse.getCourseId()) && "teacher".equals(userRepository.getById(userCourse.getLogin()).getRole()))
                .findFirst();
        return optionalUserCourse.map(userCourse -> userRepository.getById(userCourse
                .getLogin())).orElse(null);
    }

    public boolean isTeacherExists(String login) {
        return userCourseRepository.findAll().stream().anyMatch(userCourse -> userCourse.getLogin().equals(login) && "teacher".equals(userRepository.getById(login).getRole()));
    }

    public void addStudent(String login, int courseId) {
        UserCourse userCourse = new UserCourse();
        userCourse.setCourseId(courseId);
        userCourse.setLogin(login);
        userCourseRepository.save(userCourse);
    }

    public void createCourse(String login, Course course) {
        Course c = courseRepository.save(course);
        UserCourse userCourse = new UserCourse();
        userCourse.setCourseId(c.getId());
        userCourse.setLogin(login);
        userCourse.setHighestMark(0.0);
        userCourseRepository.save(userCourse);
    }

    public void deleteCourse(String courseId) {
        int id = Integer.parseInt(courseId);
        courseRepository.deleteById(id);
        List<UserCourse> userCourses = userCourseRepository.findAll()
                .stream().filter(userCourse -> id == userCourse.getCourseId()).collect(Collectors.toList());
        userCourseRepository.deleteAll(userCourses);
    }
}

package nure.ua.learn.online.services;

import lombok.AllArgsConstructor;
import nure.ua.learn.online.entities.Course;
import nure.ua.learn.online.entities.User;
import nure.ua.learn.online.entities.UserCourse;
import nure.ua.learn.online.repositories.CourseRepository;
import nure.ua.learn.online.repositories.UserCourseRepository;
import nure.ua.learn.online.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
            UserCourse userCourse = userCourseRepository.findAll()
                    .stream().filter(userCourse1 -> userCourse1.getCourseId().equals(course.getId())).findFirst().get();
            if (userCourse.getLogin().equals(login)) {
                userCourses.add(course);
            }
        }
        return userCourses;
    }

    public List<Course> getAllCourses(){
        return courseRepository.findAll();
    }

    public Map<User, List<Course>> getCourses(){
        List<User> teachers = userRepository.findAll()
                .stream().filter(user -> user.getRole().equals("teacher")).collect(Collectors.toList());
        Map<User, List<Course>> userCoursesMap = new HashMap<>();
        for(User teacher:teachers){
            List<Course> courses = getUserCourses(teacher.getLogin());
            if(!courses.isEmpty()){
                userCoursesMap.put(teacher, courses);
            }
        }
        return userCoursesMap;
    }

    public Course getCourseById(int id){
        return courseRepository.getById(id);
    }

    public User getCourseTeacher(Integer courseId){
        return userRepository.getById(userCourseRepository.findAll()
                .stream().filter(userCourse -> courseId.equals(userCourse.getCourseId()))
                .findFirst()
                .get()
                .getLogin());
    }

    public void createCourse(String login, Course course) {
        UserCourse userCourse = new UserCourse();
        userCourse.setCourseId(course.getId());
        userCourse.setLogin(login);
        userCourseRepository.save(userCourse);
        courseRepository.save(course);
    }
}

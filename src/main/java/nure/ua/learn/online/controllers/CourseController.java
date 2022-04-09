package nure.ua.learn.online.controllers;

import lombok.AllArgsConstructor;
import nure.ua.learn.online.services.UserCourseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@AllArgsConstructor
public class CourseController {

    private final UserCourseService userCourseService;

    @GetMapping("/courseList")
    public String getCourseList(HttpSession session) {
        session.setAttribute("courseList", userCourseService.getAllCourses());
        return "courseList";
    }

    @GetMapping("/course")
    public String openCourse(@RequestParam(name = "courseId") String courseId, HttpSession session) {
        int id = Integer.parseInt(courseId);
        session.setAttribute("currentCourse", userCourseService.getCourseById(id));
        session.setAttribute("currentCourseTeacher", userCourseService.getCourseTeacher(id));
        return "course";
    }
}

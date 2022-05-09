package nure.ua.learn.online.controllers;

import lombok.AllArgsConstructor;
import nure.ua.learn.online.entities.Course;
import nure.ua.learn.online.entities.User;
import nure.ua.learn.online.services.UserCourseService;
import nure.ua.learn.online.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@AllArgsConstructor
public class CourseController {

    private final UserCourseService userCourseService;

    @GetMapping("/courseList")
    public String getCourseList(@RequestParam(name = "myCourses", required = false) String myCourses, @RequestParam(name = "teacher", required = false) String teacher, HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        if ("true".equals(myCourses) && user != null) {
            session.setAttribute("courseList", userCourseService.getUserCourses(user.getLogin()));
        } else if (teacher != null && userCourseService.isTeacherExists(teacher)) {
            session.setAttribute("courseList", userCourseService.getUserCourses(teacher));
        } else {
            session.setAttribute("courseList", userCourseService.getAllCourses());
        }
        return "courseList";
    }

    @GetMapping("/course")
    public String openCourse(@RequestParam(name = "courseId") String courseId, HttpSession session) {
        int id = Integer.parseInt(courseId);
        session.setAttribute("currentCourse", userCourseService.getCourseById(id));
        session.setAttribute("currentCourseTeacher", userCourseService.getCourseTeacher(id));
        return "course";
    }

    @GetMapping("editCourse")
    public String editCourse(@RequestParam(name = "courseId", required = false) String courseId, HttpSession session) {
        if (courseId != null) {
            Course course = userCourseService.getCourseById(Integer.parseInt(courseId));
            session.setAttribute("courseForEditing", course);
        } else {
            session.removeAttribute("courseForEditing");
        }
        return "editCourse";
    }

    @PostMapping("/saveCourse")
    public RedirectView saveCourse(HttpServletRequest request) {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        Course course = new Course();
        if (!id.isEmpty()) {
            course.setId(Integer.parseInt(id));
        }
        course.setName(name);
        course.setDescription(description);
        userCourseService.createCourse(((User) request.getSession().getAttribute("currentUser")).getLogin(), course);
        return new RedirectView("courseList");
    }

    @PostMapping("/deleteCourse")
    public RedirectView deleteCourse(HttpServletRequest request) {
        String id = request.getParameter("id");
        userCourseService.deleteCourse(id);
        return new RedirectView("courseList");
    }
}

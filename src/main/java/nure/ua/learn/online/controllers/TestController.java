package nure.ua.learn.online.controllers;

import lombok.AllArgsConstructor;
import nure.ua.learn.online.DataValidator;
import nure.ua.learn.online.dto.TestData;
import nure.ua.learn.online.entities.PossibleAnswer;
import nure.ua.learn.online.entities.Question;
import nure.ua.learn.online.entities.User;
import nure.ua.learn.online.services.TestService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class TestController {

    private final TestService testService;
    private final DataValidator dataValidator;

    @GetMapping("/test")
    public String getTest(@RequestParam(name = "courseId") String courseId, HttpSession session) {
        if (!dataValidator.isNumeric(courseId)) {
            return "error";
        }
        Map<Question, List<PossibleAnswer>> questionsMap = testService.getCourseTest(Integer.parseInt(courseId));
        session.setAttribute("currentTest", questionsMap);
        return "test";
    }

    @PostMapping("/test")
    public RedirectView checkTest(HttpServletRequest request) {
        TestData testData = new TestData(request);
        double mark = testService.getMark(testData.getAnswersId());
        double maxMark = testService.getMaxTestMark(testData.getCourseId());
        HttpSession session = request.getSession();
        session.setAttribute("userMark", mark);
        session.setAttribute("maxMark", maxMark);
        testService.setHighestMark(testData.getCourseId(), ((User) session.getAttribute("currentUser")).getLogin(), mark);
        return new RedirectView("mark");
    }

    @PostMapping("/removeTest")
    public RedirectView removeTest(HttpServletRequest request) {
        String questionId = request.getParameter("questionId");
        testService.removeTest(Integer.parseInt(questionId));
        return new RedirectView("test?courseId=" + request.getParameter("courseId"));
    }

    @PostMapping("/removeAnswer")
    public RedirectView removeAnswer(HttpServletRequest request) {
        String answerId = request.getParameter("answerId");
        testService.removeAnswer(Integer.parseInt(answerId));
        return new RedirectView(request.getParameter("page"));
    }

    @GetMapping("/mark")
    public String getMark() {
        return "mark";
    }

    @GetMapping("/editTest")
    public String openTest(@RequestParam(name = "questionId", required = false) String questionId, HttpSession session) {
        if (dataValidator.isNumeric(questionId)) {
            int id = Integer.parseInt(questionId);
            session.setAttribute("currentQuestion", testService.getQuestion(id));
            session.setAttribute("currentPossibleAnswers", testService.getPossibleAnswers(id));
        } else {
            session.removeAttribute("currentQuestion");
            session.removeAttribute("currentPossibleAnswers");
        }
        return "editTest";
    }

    @PostMapping("/editQuestion")
    public RedirectView editQuestion(HttpServletRequest request) {
        String questionId = request.getParameter("questionId");
        String courseId = request.getParameter("courseId");
        String questionText = request.getParameter("questionText");
        Question question = new Question();
        question.setCourseId(Integer.parseInt(courseId));
        question.setText(questionText);
        if (dataValidator.isNumeric(questionId)) {
            question.setId(Integer.parseInt(questionId));
        }
        Question q = testService.saveQuestion(question);
        return new RedirectView("editTest?questionId=" + q.getId());
    }

    @PostMapping("/editAnswer")
    public RedirectView editAnswer(HttpServletRequest request) {
        String questionId = request.getParameter("questionId");
        String answerId = request.getParameter("answerId");
        String text = request.getParameter("text");
        String mark = request.getParameter("mark");
        PossibleAnswer answer = new PossibleAnswer();
        answer.setQuestionId(Integer.parseInt(questionId));
        answer.setText(text);
        answer.setMark(Double.parseDouble(mark));
        if (answerId != null && !"".equals(answerId)) {
            answer.setId(Integer.parseInt(answerId));
        }
        testService.saveAnswer(answer);
        return new RedirectView("editTest?questionId=" + questionId);
    }

    @GetMapping("/answer")
    public String addAnswer(@RequestParam(name = "questionId") String questionId, HttpSession session) {
        session.setAttribute("currentQuestionId", questionId);
        return "answer";
    }
}

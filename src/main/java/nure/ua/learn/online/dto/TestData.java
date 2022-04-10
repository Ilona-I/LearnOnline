package nure.ua.learn.online.dto;

import lombok.Getter;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Getter
public class TestData {

    private final List<Integer> answersId = new ArrayList<>();
    private final int courseId;

    public TestData(HttpServletRequest request) {
        String[] questions = request.getParameterValues("question");
        for (String questionId : questions) {
            answersId.add(Integer.parseInt(request.getParameter("answer" + questionId)));
        }
        courseId = Integer.parseInt(request.getParameter("courseId"));
    }
}

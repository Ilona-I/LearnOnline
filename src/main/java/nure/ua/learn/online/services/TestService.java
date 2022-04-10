package nure.ua.learn.online.services;

import lombok.AllArgsConstructor;
import nure.ua.learn.online.entities.PossibleAnswer;
import nure.ua.learn.online.entities.Question;
import nure.ua.learn.online.entities.UserCourse;
import nure.ua.learn.online.entities.keys.UserCourseId;
import nure.ua.learn.online.repositories.PossibleAnswerRepository;
import nure.ua.learn.online.repositories.QuestionRepository;
import nure.ua.learn.online.repositories.UserCourseRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TestService {

    private final QuestionRepository questionRepository;
    private final PossibleAnswerRepository possibleAnswerRepository;
    private final UserCourseRepository userCourseRepository;

    public Map<Question, List<PossibleAnswer>> getCourseTest(int courseId) {
        Map<Question, List<PossibleAnswer>> courseTest = new HashMap<>();
        List<Question> questions = questionRepository.findAll()
                .stream().filter(question -> question.getCourseId() == courseId).collect(Collectors.toList());
        for (Question question : questions) {
            List<PossibleAnswer> possibleAnswers = possibleAnswerRepository.findAll()
                    .stream().filter(possibleAnswer -> possibleAnswer.getQuestionId() == question.getId()).collect(Collectors.toList());
            courseTest.put(question, possibleAnswers);
        }
        return courseTest;
    }

    public double getMaxTestMark(int courseId) {
        double result = 0;
        List<Question> questions = questionRepository.findAll()
                .stream().filter(question -> question.getCourseId() == courseId).collect(Collectors.toList());
        for (Question question : questions) {
            List<PossibleAnswer> answers = possibleAnswerRepository.findAll()
                    .stream().filter(possibleAnswer -> possibleAnswer.getQuestionId() == question.getId()).collect(Collectors.toList());
            for (PossibleAnswer answer : answers) {
                result += answer.getMark();
            }
        }
        return result;
    }

    public double getMark(List<Integer> answersId) {
        double result = 0;
        for (Integer answerId : answersId) {
            result += possibleAnswerRepository.getById(answerId).getMark();
        }
        return result;
    }

    public double getMark(int courseId, String login) {
        return userCourseRepository.getById(new UserCourseId(courseId, login)).getHighestMark();
    }

    public void removeTest(int questionId) {
        possibleAnswerRepository.deleteAll(possibleAnswerRepository.findAll().stream().filter(possibleAnswer -> possibleAnswer.getQuestionId() == questionId).collect(Collectors.toList()));
        questionRepository.deleteById(questionId);
    }

    public void removeAnswer(int answerId) {
        possibleAnswerRepository.deleteById(answerId);
    }

    public void setHighestMark(int courseId, String login, double mark) {
        UserCourseId userCourseId = new UserCourseId(courseId, login);
        UserCourse userCourse;
        if (userCourseRepository.existsById(userCourseId)) {
            userCourse = userCourseRepository.getById(userCourseId);
        } else {
            userCourse = new UserCourse();
            userCourse.setCourseId(courseId);
            userCourse.setLogin(login);
        }
        if (userCourse.getHighestMark() == null || userCourse.getHighestMark() != null && userCourse.getHighestMark() < mark) {
            userCourse.setHighestMark(mark);
            userCourseRepository.save(userCourse);
        }
    }

    public Question getQuestion(int questionId) {
        return questionRepository.getById(questionId);
    }

    public List<PossibleAnswer> getPossibleAnswers(int questionId) {
        return possibleAnswerRepository.findAll()
                .stream().filter(possibleAnswer -> possibleAnswer.getQuestionId() == questionId).collect(Collectors.toList());
    }

    public Question saveQuestion(Question question) {
        return questionRepository.save(question);
    }

    public void saveAnswer(PossibleAnswer answer) {
        possibleAnswerRepository.save(answer);
    }
}

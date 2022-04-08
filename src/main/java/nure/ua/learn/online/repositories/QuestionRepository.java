package nure.ua.learn.online.repositories;

import nure.ua.learn.online.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository <Question, Integer> {

}

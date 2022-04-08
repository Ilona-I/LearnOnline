package nure.ua.learn.online.repositories;

import nure.ua.learn.online.entities.PossibleAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PossibleAnswerRepository extends JpaRepository<PossibleAnswer, Integer> {

}

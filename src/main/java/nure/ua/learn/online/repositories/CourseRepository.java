package nure.ua.learn.online.repositories;

import nure.ua.learn.online.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository <Course, Integer> {

}

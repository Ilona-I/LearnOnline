package nure.ua.learn.online.repositories;

import nure.ua.learn.online.entities.UserCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCourseRepository extends JpaRepository <UserCourse, Integer> {

}

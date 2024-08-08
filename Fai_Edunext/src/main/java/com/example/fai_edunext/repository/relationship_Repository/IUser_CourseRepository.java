package com.example.fai_edunext.repository.relationship_Repository;

import com.example.fai_edunext.entity.relationship.User_Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUser_CourseRepository extends JpaRepository<User_Course, Long> {
    Optional<User_Course> findUser_CourseByCourseId(Long id);
    Optional<User_Course> findUser_CourseByUserId(Long id);
}

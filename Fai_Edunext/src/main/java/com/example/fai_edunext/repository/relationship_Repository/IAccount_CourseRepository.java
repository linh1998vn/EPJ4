package com.example.fai_edunext.repository.relationship_Repository;

import com.example.fai_edunext.entity.relationship.Account_Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IAccount_CourseRepository extends JpaRepository<Account_Course, Long> {
    boolean existsAccount_CourseByAccountId(Long id);
    boolean existsAccount_CourseByCourseId(Long id);

    @Query("SELECT ac FROM Account_Course ac WHERE ac.status= ?1")
    List<Account_Course> findAllStudentRegisterCourse(String name);

    List<Account_Course> findAccount_CoursesByAccountId(Long id);
    List<Account_Course> findAccount_CoursesByCourseId(Long id);

    Optional<Account_Course> findAccount_CourseByAccountId(Long id);
}

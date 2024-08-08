package com.example.fai_edunext.repository.relationship_Repository;

import com.example.fai_edunext.entity.relationship.Course_Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ICourse_SubjectRepository extends JpaRepository<Course_Subject, Long> {
    boolean existsCourse_SubjectByCourseId(Long id);
    boolean existsCourse_SubjectBySubjectId(Long id);

    Optional<Course_Subject> findCourse_SubjectByCourseId(Long id);
    Optional<Course_Subject> findCourse_SubjectBySubjectId(Long id);

    List<Course_Subject> findCourse_SubjectsByCourseId(Long id);
    List<Course_Subject> findCourse_SubjectsBySubjectId(Long id);
}

package com.example.fai_edunext.repository.relationship_Repository;

import com.example.fai_edunext.entity.relationship.Course_Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ICourse_ClassroomRepository extends JpaRepository<Course_Classroom, Long> {
    boolean existsCourse_ClassroomByCourseId(Long id);
    boolean existsCourse_ClassroomByClassroomId(Long id);

    List<Course_Classroom> findCourse_ClassroomsByClassroomId(Long id);
    List<Course_Classroom> findCourse_ClassroomsByCourseId(Long id);

    Optional<Course_Classroom> findCourse_ClassroomByCourseId(Long id);
    boolean existsAccount_CourseByStatus(String name);


}

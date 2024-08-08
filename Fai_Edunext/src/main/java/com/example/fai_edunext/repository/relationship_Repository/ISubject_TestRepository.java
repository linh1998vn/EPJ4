package com.example.fai_edunext.repository.relationship_Repository;

import com.example.fai_edunext.entity.relationship.Subject_Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ISubject_TestRepository extends JpaRepository<Subject_Test, Long> {
    boolean existsSubject_TestBySubjectId(Long id);
    boolean existsSubject_TestByTestId(Long id);

    List<Subject_Test> findSubject_TestsByTestId(Long id);
    List<Subject_Test> findSubject_TestsBySubjectId(Long id);
}

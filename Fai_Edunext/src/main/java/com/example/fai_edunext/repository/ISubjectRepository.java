package com.example.fai_edunext.repository;

import com.example.fai_edunext.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ISubjectRepository extends JpaRepository<Subject, Long> {
    @Query("SELECT s FROM Subject s WHERE " +
            "s.subjectName LIKE CONCAT('%',:query, '%')" +
            "OR s.subjectDescription LIKE CONCAT('%',:query, '%')"
    )
    List<Subject> searchSubject(String query);

    @Query("SELECT s FROM Subject s WHERE s.isDelete = ?1")
    List<Subject> findAllSubject(boolean isDelete);

    boolean existsBySubjectName(String name);
    boolean existsBySubjectCode(String code);
}

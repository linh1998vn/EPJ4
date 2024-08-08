package com.example.fai_edunext.repository;

import com.example.fai_edunext.entity.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IClassroomRepository extends JpaRepository<Classroom, Long> {
    boolean existsByClassroomName (String classname);

    @Query("select c from Classroom c where c.status = ?1")
    List<Classroom> findAllClassByStatus(String status);

    @Query("SELECT c FROM Classroom c WHERE " +
            "c.classroomName LIKE CONCAT('%',:query, '%')")
    List<Classroom> searchClassrooms(String query);
}

package com.example.fai_edunext.repository;

import com.example.fai_edunext.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICourseRepository extends JpaRepository<Course, Long> {
    boolean existsByCode(String code);
    boolean existsByName(String name);
}

package com.example.fai_edunext.repository;

import com.example.fai_edunext.entity.TeacherInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITeacherDetailRepository extends JpaRepository<TeacherInformation, Long> {
    boolean existsByPhone(String phone);
}

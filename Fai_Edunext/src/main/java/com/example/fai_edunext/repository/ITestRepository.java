package com.example.fai_edunext.repository;

import com.example.fai_edunext.entity.Subject;
import com.example.fai_edunext.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ITestRepository extends JpaRepository<Test, Long> {
    boolean existsByTestName(String name);

    @Query("SELECT t FROM Test t WHERE t.isDelete = ?1")
    List<Test> findAllTest(boolean isDelete);

    @Query("SELECT t FROM Test t WHERE " +
            "t.testName LIKE CONCAT('%',:query, '%')" +
            "OR t.type LIKE CONCAT('%',:query, '%')"
    )
    List<Test> searchTest(String query);
}

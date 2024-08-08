package com.example.fai_edunext.repository.relationship_Repository;

import com.example.fai_edunext.entity.relationship.Account_Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IAccount_ClassroomRepository extends JpaRepository<Account_Classroom, Long> {
    boolean existsAccount_ClassroomByAccountId(Long id);
    boolean existsAccount_ClassroomByClassroomId(Long id);
    List<Account_Classroom> findAccount_ClassroomsByAccountId(Long id);
    List<Account_Classroom> findAccount_ClassroomsByClassroomId(Long id);

    Optional<Account_Classroom> findAccount_ClassroomByAccountId(Long id);
    Optional<Account_Classroom> findAccount_ClassroomByClassroomId(Long id);
}

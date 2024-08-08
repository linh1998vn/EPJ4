package com.example.fai_edunext.repository.relationship_Repository;

import com.example.fai_edunext.entity.relationship.Account_Teacher_Detail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IAccount_Teacher_InformationRepository extends JpaRepository<Account_Teacher_Detail, Long> {
    boolean existsAccount_Teacher_DetailByAccountId(Long id);
    boolean existsAccount_Teacher_DetailByTeacherDetail(Long id);

    Optional<Account_Teacher_Detail> findAccount_Teacher_DetailByAccountId(Long id);
    Optional<Account_Teacher_Detail> findAccount_Teacher_DetailByTeacherDetail(Long id);

}

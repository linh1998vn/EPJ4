package com.example.fai_edunext.repository.relationship_Repository;

import com.example.fai_edunext.dto.inter.IAccount_Test;
import com.example.fai_edunext.entity.relationship.Account_Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IAccount_SubjectRepository extends JpaRepository<Account_Subject, Long> {
    List<Account_Subject> findAccount_SubjectsByAccountId(Long id);
    List<Account_Subject> findAccount_SubjectsBySubjectId(Long id);

    boolean existsAccount_SubjectByAccountId(Long id);
    boolean existsAccount_SubjectBySubjectId(Long id);

    @Query(value = "select a.id as id, a.user_name as name, a.code, s.subject_name as subject, t.test_name as test " +
            "from accounts a, accounts_subjects acs, subjects_tests st, tests t, subjects s " +
            "WHERE st.subject_id = s.id and a.id = acs.account_id and acs.subject_id = st.subject_id and st.test_id = t.id " +
            "and a.id = :id ", nativeQuery = true)
    List<IAccount_Test> getListAccountTest(Long id);
}

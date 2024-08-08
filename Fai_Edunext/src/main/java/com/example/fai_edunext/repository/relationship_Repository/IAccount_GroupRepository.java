package com.example.fai_edunext.repository.relationship_Repository;

import com.example.fai_edunext.entity.relationship.Account_Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IAccount_GroupRepository extends JpaRepository<Account_Group, Long> {
    List<Account_Group> findAccount_GroupsByAccountId(Long id);
    List<Account_Group> findAccount_GroupsByGroupId(Long id);

    Optional<Account_Group> findAccount_GroupById(Long id);
    Optional<Account_Group> findAccount_GroupByAccountId(Long id);
    Optional<Account_Group> findAccount_GroupByGroupId(Long id);

    boolean existsAccount_GroupByAccountId(Long id);
    boolean existsAccount_GroupByGroupId(Long id);

    Optional<Account_Group> deleteAccount_GroupByAccountId(Long id);
}

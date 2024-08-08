package com.example.fai_edunext.repository.relationship_Repository;

import com.example.fai_edunext.entity.relationship.Account_User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IAccount_UserRepository extends JpaRepository<Account_User, Long> {
    boolean existsAccount_UserByUserId (Long id);
    Optional<Account_User> findAccount_UserByAccountId(Long id);
    Optional<Account_User> findAccount_UserByUserId(Long id);
}

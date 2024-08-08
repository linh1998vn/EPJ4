package com.example.fai_edunext.repository;

import com.example.fai_edunext.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IAccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUsername(String username);
    boolean existsByUsername (String username);

    @Query("SELECT a FROM Account a WHERE a.position = ?1")
    List<Account> findAllTeacher(String name);

    @Query("SELECT a FROM Account a WHERE a.position = 'student'")
    List<Account> findAllAccountStudent();
}

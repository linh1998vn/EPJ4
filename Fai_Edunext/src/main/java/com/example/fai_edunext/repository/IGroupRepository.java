package com.example.fai_edunext.repository;

import com.example.fai_edunext.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IGroupRepository extends JpaRepository<Group, Long> {
    boolean existsByGroupName(String name);
    Optional<Group> findByGroupName (String name);
}

package com.example.fai_edunext.repository.relationship_Repository;

import com.example.fai_edunext.entity.relationship.Classroom_Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IClassroom_GroupRepository extends JpaRepository<Classroom_Group, Long> {
    List<Classroom_Group> findClassroom_GroupsByClassroomId(Long id);
    List<Classroom_Group> findClassroom_GroupsByGroupId(Long id);

    Optional<Classroom_Group> findClassroom_GroupByClassroomId(Long id);
    Optional<Classroom_Group> findClassroom_GroupByGroupId(Long id);

    boolean existsClassroom_GroupByClassroomId(Long id);
    boolean existsClassroom_GroupByGroupId(Long id);
}

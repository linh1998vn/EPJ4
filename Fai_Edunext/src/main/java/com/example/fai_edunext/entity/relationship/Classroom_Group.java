package com.example.fai_edunext.entity.relationship;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "classrooms_groups")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Classroom_Group {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "classroom_id")
    private Long classroomId;

    @Column(name = "group_id")
    private Long groupId;

    private boolean status;
}

package com.example.fai_edunext.entity.relationship;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "subjects_tests")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Subject_Test {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "subject_id")
    private Long subjectId;

    @Column(name = "test_id")
    private Long testId;
}

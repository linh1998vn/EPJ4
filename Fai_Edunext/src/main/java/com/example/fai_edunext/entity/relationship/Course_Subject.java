package com.example.fai_edunext.entity.relationship;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "courses_subjects")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Course_Subject {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "course_id")
    private Long courseId;

    @Column(name = "subject_id")
    private Long subjectId;

    @Column(name = "status")
    private String status;
}

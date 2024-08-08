package com.example.fai_edunext.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "subjects")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Subject {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "subject_name")
    private String subjectName;

    @Column(name = "subject_code")
    private String subjectCode;

    @Column(name = "description")
    private String subjectDescription;

    @Column(name = "number_of_lessions")
    private int numberOfLessons;

    @Column(name = "isDelete")
    private boolean isDelete;
}

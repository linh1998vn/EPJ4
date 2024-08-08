package com.example.fai_edunext.entity.relationship;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "accounts_courses")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Account_Course {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "course_id")
    private Long courseId;

    @Column(name = "status")
    private String status;
}

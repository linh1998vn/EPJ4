package com.example.fai_edunext.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "courses")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "course_name")
    private String name;

    @Column(name = "course_code")
    private String code;

    @Column(name = "course_description")
    private String description;

//    @Column(name = "course_time")
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private LocalDate startDate;

    @Column(name = "course_time")
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String time;

}

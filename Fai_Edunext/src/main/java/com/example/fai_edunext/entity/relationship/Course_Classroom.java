package com.example.fai_edunext.entity.relationship;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "courses_classrooms")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Course_Classroom {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "course_id")
    private Long courseId;

    @Column(name = "classroom_id")
    private Long classroomId;

//    @Column(name = "start_time")
//    @DateTimeFormat(pattern = "dd-MM-yyyy")
//    private LocalDate startTime;
//
//    @Column(name = "end_time")
//    @DateTimeFormat(pattern = "dd-MM-yyyy")
//    private LocalDate endTime;

    @Column(name = "status")
    private String status;
}

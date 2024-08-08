package com.example.fai_edunext.dto.request;

import com.example.fai_edunext.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CourseRequest {
    private Long courseId;
    private String name;
    private String code;
    private String description;
    private String time;

    public CourseRequest(Course course){
        this.name = course.getName();
        this.code = course.getCode();
        this.description = course.getDescription();
        this.time = course.getTime();
    }
}

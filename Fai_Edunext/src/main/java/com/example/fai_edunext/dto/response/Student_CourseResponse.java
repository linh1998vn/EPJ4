package com.example.fai_edunext.dto.response;

import com.example.fai_edunext.dto.request.CourseRequest;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Student_CourseResponse {
    private String username;
    private String code;
    private CourseRequest course;
    private String status;
}

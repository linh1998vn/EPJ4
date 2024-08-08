package com.example.fai_edunext.dto.response;

import com.example.fai_edunext.dto.request.ClassroomRequest;
import com.example.fai_edunext.dto.request.CourseRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Course_ClassroomResponse {
    private String courseName;
    private String code;
    private List<ClassroomRequest> classrooms;
}

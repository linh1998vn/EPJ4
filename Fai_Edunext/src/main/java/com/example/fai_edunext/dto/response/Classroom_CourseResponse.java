package com.example.fai_edunext.dto.response;

import com.example.fai_edunext.dto.request.CourseRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Classroom_CourseResponse {
    private String classroomName;
    private List<CourseRequest> courses;

}

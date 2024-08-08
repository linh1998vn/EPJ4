package com.example.fai_edunext.dto.response;

import com.example.fai_edunext.dto.request.ClassroomRequest;
import com.example.fai_edunext.dto.request.StudentRequest;
import com.example.fai_edunext.dto.request.TeacherRequest;
import com.example.fai_edunext.entity.Classroom;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Attend_Class_Teacher_StudentsResponse {
    private String className;
    // teacher
    private TeacherRequest teacher;
    // classroom
    // student
    private List<StudentRequest> students;
}

package com.example.fai_edunext.dto.response;


import com.example.fai_edunext.dto.request.StudentRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Classroom_StudentsResponse {
    private String classroomName;
    private List<StudentResponse> students;
}

package com.example.fai_edunext.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddStudents_ClassroomRequest {
    private Long classroomId;
    private List<StudentRequest> students;
}

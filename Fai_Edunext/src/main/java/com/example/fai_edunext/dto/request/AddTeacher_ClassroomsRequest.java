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
public class AddTeacher_ClassroomsRequest {
    private Long teacherId;
    private List<ClassroomRequest> classrooms;
}

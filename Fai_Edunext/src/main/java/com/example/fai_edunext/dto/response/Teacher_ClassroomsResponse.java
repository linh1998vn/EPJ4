package com.example.fai_edunext.dto.response;

import com.example.fai_edunext.dto.request.ClassroomRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Teacher_ClassroomsResponse {
    private String username;
    private String code;
    private List<ClassroomRequest> classrooms;
}

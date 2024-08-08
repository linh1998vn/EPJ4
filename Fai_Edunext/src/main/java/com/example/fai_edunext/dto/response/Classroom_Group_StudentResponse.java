package com.example.fai_edunext.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Classroom_Group_StudentResponse {
    private String classroomName;
    private List<Group_StudentResponse> group_studentResponses;
}

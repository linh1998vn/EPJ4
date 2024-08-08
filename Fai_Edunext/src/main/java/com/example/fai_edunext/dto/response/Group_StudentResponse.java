package com.example.fai_edunext.dto.response;

import com.example.fai_edunext.dto.request.StudentRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Group_StudentResponse {
    private String groupName;
    private List<StudentRequest> students;
}

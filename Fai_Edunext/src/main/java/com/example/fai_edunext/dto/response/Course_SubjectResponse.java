package com.example.fai_edunext.dto.response;

import com.example.fai_edunext.dto.request.SubjectRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Course_SubjectResponse {
    private String course_name;
    private List<SubjectRequest> subjects;
}

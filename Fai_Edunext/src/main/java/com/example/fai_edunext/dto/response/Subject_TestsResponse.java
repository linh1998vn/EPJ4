package com.example.fai_edunext.dto.response;

import com.example.fai_edunext.dto.request.TestRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Subject_TestsResponse {
    private String subjectName;
    private String subjectCode;
    private List<TestRequest> tests;
}

package com.example.fai_edunext.dto.response;

import com.example.fai_edunext.dto.request.TeacherDetailInformationRequest;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Teacher_InformationResponse {
    private String username;
    private String code;

    private TeacherDetailInformationRequest information;
}

package com.example.fai_edunext.dto.response;

import com.example.fai_edunext.dto.request.StudentRequest;
import com.example.fai_edunext.dto.request.UserRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudentInformationResponse {
    private StudentRequest student;
    private UserRequest information;
}

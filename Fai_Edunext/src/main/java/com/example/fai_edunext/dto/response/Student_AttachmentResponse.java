package com.example.fai_edunext.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Student_AttachmentResponse {
    private String name;
    private String code;
    private AttachmentResponse attachment;
}

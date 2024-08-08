package com.example.fai_edunext.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponse {
    private Long studentId;
    // lay tu bang user
    private String full_name;

    // Account
    private String username;
    private String code;
    private String position;
}

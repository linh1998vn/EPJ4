package com.example.fai_edunext.command;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterStudent_CourseCommand {
    private Long studentId;
    private Long courseId;
}

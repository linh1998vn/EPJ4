package com.example.fai_edunext.dto.request;

import com.example.fai_edunext.entity.Subject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
public class SubjectRequest {
    private Long subjectId;
    private String subjectName;
    private String subjectCode;
    private String subjectDescription;
    private int numberOfLessons;

    public SubjectRequest(Subject subject){
        this.subjectName = subject.getSubjectName();
        this.subjectCode = subject.getSubjectCode();
        this.subjectDescription = subject.getSubjectDescription();
        this.numberOfLessons = subject.getNumberOfLessons();
    }
}

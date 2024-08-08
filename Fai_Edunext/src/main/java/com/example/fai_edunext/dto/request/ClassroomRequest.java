package com.example.fai_edunext.dto.request;

import com.example.fai_edunext.entity.Classroom;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClassroomRequest {
    private Long classroomId;
    private String classroomName;
    private String createdDate;
    private String  updatedDate;
    private String status;

    public ClassroomRequest(Classroom classroom){
        this.classroomName = classroom.getClassroomName();
        this.createdDate = classroom.getCreatedDate();
        this.updatedDate = classroom.getUpdatedDate();
        this.status = classroom.getStatus();
    }
}

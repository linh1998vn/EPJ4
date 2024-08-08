package com.example.fai_edunext.dto.request;

import com.example.fai_edunext.entity.TeacherInformation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
public class TeacherDetailInformationRequest {
    private Long id;

    private String firstName;

    private String lastName;

    private String phone;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    private String address;

    private String degree;

    public TeacherDetailInformationRequest(TeacherInformation teacherInformation){
        this.firstName = teacherInformation.getFirstName();
        this.lastName = teacherInformation.getLastName();
        this.phone = teacherInformation.getPhone();
        this.birthday = teacherInformation.getBirthday();
        this.address = teacherInformation.getAddress();
        this.degree = teacherInformation.getDegree();
    }
}

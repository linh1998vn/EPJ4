package com.example.fai_edunext.dto.request;

import com.example.fai_edunext.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User_CourseRequest {
    private Long id;

    private String firstName;

    private String lastName;

    private String phone;

    private String address;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    // thong tin course
    private Long courseId;

    private String status;

    public User_CourseRequest(User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.phone = user.getPhone();
        this.address = user.getAddress();
        this.birthday = user.getBirthday();
    }
}

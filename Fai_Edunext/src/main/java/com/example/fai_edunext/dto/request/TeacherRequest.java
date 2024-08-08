package com.example.fai_edunext.dto.request;

import com.example.fai_edunext.entity.Account;
import com.example.fai_edunext.entity.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
public class TeacherRequest {
    private Long studentId;
    private String username;
    private String code;
    private String position;


    public TeacherRequest(Account account) {
        this.username = account.getUsername();
        this.code = account.getCode();
        this.position = account.getPosition();
    }
}

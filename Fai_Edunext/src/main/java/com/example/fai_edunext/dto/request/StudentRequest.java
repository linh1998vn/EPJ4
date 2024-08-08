package com.example.fai_edunext.dto.request;

import com.example.fai_edunext.entity.Account;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
public class StudentRequest {
    private Long studentId;
    // lay tu bang user
    private String username;
    private String code;
    private String position;

    public StudentRequest(Account account) {
        this.username = account.getUsername();
        this.code = account.getCode();
        this.position = account.getPosition();
    }
}

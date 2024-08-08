package com.example.fai_edunext.dto.response;

import lombok.Data;

@Data
public class AccountTestDTO {
    private String id;
    private String name;
    private String code;
    private String subject;
    private String test;

    public AccountTestDTO(){};

    public AccountTestDTO(String id, String name, String code, String subject, String test) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.subject = subject;
        this.test = test;
    }
}

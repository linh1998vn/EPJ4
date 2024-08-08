package com.example.fai_edunext.dto.request;

import com.example.fai_edunext.entity.Test;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


@Setter
@Getter
@NoArgsConstructor
public class TestRequest {
    private Long testId;
    private String testName;
    private String type;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDate dueDate;
    private boolean isDelete;
    //    private String file;

    public TestRequest(Test test){
        this.testName = test.getTestName();
        this.type = test.getType();
        this.dueDate = test.getDueDate();
    }
}

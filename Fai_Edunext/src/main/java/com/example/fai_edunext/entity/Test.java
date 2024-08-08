package com.example.fai_edunext.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tests")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Test {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "test_name")
    private String testName;

    @Column(name = "file")
    private String file;

    @Column(name = "test_type")
    private String type;

    @Column(name = "due_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDate dueDate;

    @Column(name = "test_point")
    private int point;

    @Column(name = "test_is_delete")
    private boolean isDelete;
}

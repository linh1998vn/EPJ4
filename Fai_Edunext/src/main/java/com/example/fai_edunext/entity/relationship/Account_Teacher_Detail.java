package com.example.fai_edunext.entity.relationship;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "accounts_teacher_details")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Account_Teacher_Detail {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "teacher_detail_id")
    private Long teacherDetail;
}

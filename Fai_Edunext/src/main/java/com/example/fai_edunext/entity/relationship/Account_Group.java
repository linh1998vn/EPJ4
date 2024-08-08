package com.example.fai_edunext.entity.relationship;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "accounts_groups")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Account_Group {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "group_id")
    private Long groupId;

    private boolean status;
}

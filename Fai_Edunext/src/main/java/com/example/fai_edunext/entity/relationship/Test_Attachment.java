package com.example.fai_edunext.entity.relationship;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tests_attachments")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Test_Attachment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "test_id")
    private Long testId;

    @Column(name = "attachment_id")
    private Long attachmentId;
}

package com.example.fai_edunext.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "attachments")
@Data
@Setter
@Getter
@NoArgsConstructor
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;
    private String fileType;

    @Lob
    private byte[] data;

    @Column(name = "submit_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String submitTime;

    public Attachment(String fileName, String fileType, byte[] data, String submitTime) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
        this.submitTime = submitTime;
    }
}

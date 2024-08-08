package com.example.fai_edunext.dto.response;

import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AttachmentResponse {
    private String fileName;
    private String downloadURL;
    private String fileType;
    private long fileSize;
    private String submitTime;
}

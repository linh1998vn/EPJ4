package com.example.fai_edunext.services;

import com.example.fai_edunext.entity.Attachment;
import com.example.fai_edunext.repository.IAttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class AttachmentService {
    @Autowired
    IAttachmentRepository iAttachmentRepository;

    public Attachment saveAttachment(MultipartFile file) throws Exception {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if(fileName.contains("..")) {
                throw  new Exception("Filename contains invalid path sequence "
                        + fileName);
            }
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            String submitTime = dateFormat.format(date);

            Attachment attachment = new Attachment(fileName,
                    file.getContentType(),
                    file.getBytes(),
                    submitTime);
            return iAttachmentRepository.save(attachment);

        } catch (Exception e) {
            throw new Exception("Could not save File: " + fileName);
        }
    }

    public Attachment getAttachment(Long fileId) throws Exception {
        return iAttachmentRepository.findById(fileId)
                .orElseThrow(
                        () -> new Exception("File not found with Id: " + fileId));
    }
}

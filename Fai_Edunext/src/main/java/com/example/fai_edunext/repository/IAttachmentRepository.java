package com.example.fai_edunext.repository;

import com.example.fai_edunext.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAttachmentRepository extends JpaRepository<Attachment, Long> {
}

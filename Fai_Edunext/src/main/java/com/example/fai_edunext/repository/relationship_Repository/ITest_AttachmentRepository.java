package com.example.fai_edunext.repository.relationship_Repository;

import com.example.fai_edunext.entity.relationship.Test_Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITest_AttachmentRepository extends JpaRepository<Test_Attachment, Long> {
    boolean existsTest_AttachmentByAttachmentId(Long id);
    boolean existsTest_AttachmentByTestId(Long id);
}

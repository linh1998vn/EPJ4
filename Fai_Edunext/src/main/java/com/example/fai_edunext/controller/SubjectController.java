package com.example.fai_edunext.controller;

import com.example.fai_edunext.dto.request.SubjectRequest;
import com.example.fai_edunext.dto.request.TestRequest;
import com.example.fai_edunext.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subject")
public class SubjectController {
    @Autowired
    SubjectService subjectService;

    // admin thì sẽ được xem tất cả môn học
    // @PreAuthorize("hasAuthority('admin') or hasAuthority('teacher')")
    @GetMapping("/get-all-subject")
    public ResponseEntity<?>getAllSubjects(){
        return subjectService.getAllSubject();
    }

    // @PreAuthorize("hasAuthority('admin') or hasAuthority('teacher')")
    @GetMapping("/get-subject")
    public ResponseEntity<?>getSubjectById(@RequestParam(name = "subjectId") Long id){
        return subjectService.getSubjectById(id);
    }

    // @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/get-all-subject-with-test")
    public ResponseEntity<?>showAllSubjectWithTest(){
        return subjectService.showAllSubjectWithTest();
    }

    // @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/get-subject-to-test")
    public ResponseEntity<?>getSubjectToTestBySubjectId(@RequestParam(name = "subjectId") Long id){
        return subjectService.getDetailSubjectWithTestBySubjectId(id);
    }

    // @PreAuthorize("hasAuthority('admin') or hasAuthority('teacher')")
    @GetMapping("/search")
    public ResponseEntity<?>searchSubject(@RequestParam(name = "query") String query){
        return subjectService.searchSubject(query);
    }

    // tài khoản admin thì mới được tạo
    // @PreAuthorize("hasAuthority('admin')")
    @PostMapping("/create-subject")
    public ResponseEntity<?>saveSubject(@Validated @RequestBody SubjectRequest subjectRequest){
        return subjectService.createSubject(subjectRequest);
    }

    // tài khoản admin thì mới được update
    // @PreAuthorize("hasAuthority('admin')")
    @PutMapping("/update-subject")
    public ResponseEntity<?>updateSubject(@Validated @RequestParam(name = "subjectId") Long id, @RequestBody SubjectRequest subjectRequest){
        return subjectService.updateSubject(id, subjectRequest);
    }

    // Tìm ra subject và tạo bài kiểm tra
    // @PreAuthorize("hasAuthority('teacher')")
    @PostMapping("/add-tests-to-subject")
    public ResponseEntity<?>addTestsToSubject(@RequestParam(name = "subject") Long id,@RequestBody TestRequest testRequest){
        return subjectService.addTestsToSubject(id, testRequest);
    }

    // @PreAuthorize("hasAuthority('admin')")
    @DeleteMapping("/delete-subject")
    public ResponseEntity<?> deleteSubject(@RequestParam(name = "subjectId") Long id){
        return subjectService.deleteSubjectById(id);
    }
}

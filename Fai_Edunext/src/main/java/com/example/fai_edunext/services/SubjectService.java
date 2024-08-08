package com.example.fai_edunext.services;

import com.example.fai_edunext.dto.request.SubjectRequest;
import com.example.fai_edunext.dto.request.TestRequest;
import com.example.fai_edunext.dto.response.MessageResponse;
import com.example.fai_edunext.dto.response.Subject_TestsResponse;
import com.example.fai_edunext.entity.Subject;
import com.example.fai_edunext.entity.Test;
import com.example.fai_edunext.entity.relationship.Subject_Test;
import com.example.fai_edunext.repository.ISubjectRepository;
import com.example.fai_edunext.repository.relationship_Repository.ISubject_TestRepository;
import com.example.fai_edunext.repository.ITestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class SubjectService {
    @Autowired
    ISubjectRepository iSubjectRepository;

    @Autowired
    ITestRepository iTestRepository;

    @Autowired
    ISubject_TestRepository iSubject_testRepository;

    public ResponseEntity<?> getAllSubject() {
        var subjects = iSubjectRepository.findAllSubject(false);
        if (Objects.isNull(subjects)) {
            return ResponseEntity.badRequest().body(new MessageResponse("subject not record"));
        }
        return ResponseEntity.ok().body(subjects);
    }

    public ResponseEntity<?> getSubjectById(Long subjectId) {
        var find_subject = iSubjectRepository.findById(subjectId);
        if (find_subject.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("subject not exists"));
        }
        return ResponseEntity.ok().body(find_subject.get());
    }

    public ResponseEntity<?> createSubject(SubjectRequest subjectRequest) {
        Subject subject = new Subject();
        subject.setSubjectName(subjectRequest.getSubjectName());
        if (iSubjectRepository.existsBySubjectName(subject.getSubjectName())) {
            return ResponseEntity.badRequest().body(new MessageResponse("subject already exists"));
        }
        subject.setSubjectCode(subjectRequest.getSubjectCode());
        if (iSubjectRepository.existsBySubjectCode(subject.getSubjectCode())) {
            return ResponseEntity.badRequest().body(new MessageResponse("subject code already exists"));
        }
        subject.setSubjectDescription(subjectRequest.getSubjectDescription());
        subject.setNumberOfLessons(subjectRequest.getNumberOfLessons());
        iSubjectRepository.save(subject);
        return ResponseEntity.ok().body(new MessageResponse("Create subject successfully"));
    }

    public ResponseEntity<?> updateSubject(Long subjectId, SubjectRequest subjectRequest) {
        var find_subject = iSubjectRepository.findById(subjectId);
        if (find_subject.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("subject not exists"));
        }
        Subject subject = find_subject.get();
        if (subjectRequest.getSubjectName() == null) {
            subject.setSubjectName(subject.getSubjectName());
        } else {
            if (iSubjectRepository.existsBySubjectName(subject.getSubjectName())) {
                return ResponseEntity.badRequest().body(new MessageResponse("subject already exists"));
            }
            subject.setSubjectName(subjectRequest.getSubjectName());
        }
        if (subjectRequest.getSubjectCode() == null) {
            subject.setSubjectCode(subject.getSubjectCode());
        } else {
            if (iSubjectRepository.existsBySubjectCode(subject.getSubjectCode())) {
                return ResponseEntity.badRequest().body(new MessageResponse("subject already exists"));
            }
            subject.setSubjectCode(subjectRequest.getSubjectCode());
        }
        subject.setSubjectDescription(subjectRequest.getSubjectDescription());
        subject.setNumberOfLessons(subjectRequest.getNumberOfLessons());
        iSubjectRepository.save(subject);
        return ResponseEntity.ok().body(new MessageResponse("Update subject successfully"));
    }

    public ResponseEntity<?> deleteSubjectById(Long subjectId) {
        var find_subject = iSubjectRepository.findById(subjectId);
        if (find_subject.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("subject not exists"));
        }
        Subject subject = find_subject.get();
        subject.setDelete(true);
        iSubjectRepository.save(subject);
        return ResponseEntity.ok().body(new MessageResponse("delete subject successfully"));
    }

    public ResponseEntity<?> searchSubject(String query) {
        var search = iSubjectRepository.searchSubject(query);
        return ResponseEntity.ok().body(search);
    }

    public ResponseEntity<?> addTestsToSubject(Long id, TestRequest testRequest) {
        var checkSubject = iSubjectRepository.findById(id).get();
        if (Objects.isNull(checkSubject)) {
            return ResponseEntity.badRequest().body(new MessageResponse("subject not exists"));
        }
        Test test = new Test();
        test.setTestName(testRequest.getTestName());
        if (iTestRepository.existsByTestName(test.getTestName())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Test already exists"));
        }
        test.setType(testRequest.getType());
        test.setDueDate(testRequest.getDueDate());
        iTestRepository.save(test);
        Subject_Test subject_test = new Subject_Test();
        subject_test.setSubjectId(checkSubject.getId());
        subject_test.setTestId(test.getId());
        iSubject_testRepository.save(subject_test);
        return ResponseEntity.ok().body(new MessageResponse("add tests for subject successfully"));
    }

    public ResponseEntity<?> showAllSubjectWithTest() {
        var subjects = iSubjectRepository.findAll();
        if (Objects.isNull(subjects)) {
            return ResponseEntity.badRequest().body(new MessageResponse("show subject with test not record"));
        }
        List<Subject_TestsResponse> subject_testsResponses = new ArrayList<>();
        for (Subject subject : subjects) {
            Subject_TestsResponse subject_testsResponse = new Subject_TestsResponse();

            subject_testsResponse.setSubjectName(subject.getSubjectName());
            subject_testsResponse.setSubjectCode(subject.getSubjectCode());

            List<Subject_Test> subject_tests = iSubject_testRepository.findSubject_TestsBySubjectId(subject.getId());
            List<TestRequest> testRequests = new ArrayList<>();

            for (Subject_Test subject_test : subject_tests) {
                var test = iTestRepository.findById(subject_test.getTestId()).get();
                TestRequest testRequest = new TestRequest(test);
                testRequests.add(testRequest);
            }
            subject_testsResponse.setTests(testRequests);
            subject_testsResponses.add(subject_testsResponse);
        }
        return ResponseEntity.ok().body(subject_testsResponses);
    }

    public ResponseEntity<?> getDetailSubjectWithTestBySubjectId(Long id) {
        var subject = iSubjectRepository.findById(id).get();
        if (Objects.isNull(subject)) {
            return ResponseEntity.badRequest().body(new MessageResponse("show subject with test not record"));
        }
        Subject_TestsResponse subject_testsResponse = new Subject_TestsResponse();

        subject_testsResponse.setSubjectName(subject.getSubjectName());
        subject_testsResponse.setSubjectCode(subject.getSubjectCode());

        List<Subject_Test> subject_tests = iSubject_testRepository.findSubject_TestsBySubjectId(subject.getId());
        List<TestRequest> testRequests = new ArrayList<>();

        for (Subject_Test subject_test : subject_tests) {
            var test = iTestRepository.findById(subject_test.getTestId()).get();
            TestRequest testRequest = new TestRequest(test);
            testRequests.add(testRequest);
        }
        subject_testsResponse.setTests(testRequests);
        return ResponseEntity.ok().body(subject_testsResponse);
    }

//    public ResponseEntity<?> getSubjectToTestPoint(Long id, Subject_PointResponse subject_pointResponse) {
//        var subject = iSubjectRepository.findById(id).get();
//        if (Objects.isNull(subject)){
//            return ResponseEntity.badRequest().body(new MessageResponse("subject not exists"));
//        }
//
//        subject_pointResponse.setSubjectName(subject.getSubjectName());
//        var findtests = iSubject_testRepository.findSubject_TestsBySubjectId(subject.getId());
//        for (Subject_Test subject_test : findtests){
//            var test = iTestRepository.findById(subject_test.getTestId()).get();
//        }
//    }

    // 1 môn học sẽ có nhiều diểm
//    public ResponseEntity<?> addPointsToSubject()
}

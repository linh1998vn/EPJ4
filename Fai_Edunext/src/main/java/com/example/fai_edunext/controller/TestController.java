package com.example.fai_edunext.controller;

import com.example.fai_edunext.dto.request.TestRequest;
import com.example.fai_edunext.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/test")
public class TestController {
    @Autowired
    TestService testService;

    // @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/get-all-test")
    public ResponseEntity<?> getAllTests(){
        return testService.getAllTest();
    }

    // @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/get-test")
    public ResponseEntity<?>getTestById(@RequestParam(name = "testId") Long id){
        return testService.getTestById(id);
    }

    // @PreAuthorize("hasAuthority('teacher')")
    @PostMapping(value = "/create-test", headers = "Content-Type=multipart/form-data")
    public ResponseEntity<?>saveTest(@RequestParam(value = "testName", required = true) String testName,
                                     @RequestParam(value = "type", required = true) String type,
                                     @RequestParam(value = "dueDate", required = true) String dueDate,
                                     @RequestParam(value = "file", required = true) MultipartFile file){
        Boolean result = testService.createTestAll(testName, type, dueDate, file);
        return new ResponseEntity<>(result, HttpStatus.OK);
//        return testService.createTest(testRequest);
    }

    // @PreAuthorize("hasAuthority('teacher')")
    @PutMapping("/update-test")
    public ResponseEntity<?>updateTest(@Validated @RequestParam(name = "testId") Long id, @RequestBody TestRequest testRequest){
        return testService.updateTest(id, testRequest);
    }

    // @PreAuthorize("hasAuthority('admin')")
    @DeleteMapping("/delete-test")
    public ResponseEntity<?> deleteTest(@RequestParam(name = "testId") Long id){
        return testService.deleteTestById(id);
    }

    // @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/search")
    public ResponseEntity<?>searchTest(@RequestParam(name = "query") String query){
        return testService.searchTest(query);
    }

}

package com.example.fai_edunext.controller;

import com.example.fai_edunext.command.RegisterStudent_CourseCommand;
import com.example.fai_edunext.dto.request.AddStudents_ClassroomRequest;
import com.example.fai_edunext.dto.response.AttachmentResponse;
import com.example.fai_edunext.dto.response.Student_AttachmentResponse;
import com.example.fai_edunext.entity.Account;
import com.example.fai_edunext.entity.Attachment;
import com.example.fai_edunext.entity.relationship.Account_Attachment;
import com.example.fai_edunext.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    // lay ra thong tin cua tat ca account hoc sinh
    // @PreAuthorize("hasAuthority('admin')")
    @GetMapping("get-all-student")
    public ResponseEntity<?> getAllStudent(){
        return studentService.getAllStudent();
    }

    // lay ra tat ca chi tiet thong tin cua tat ca hoc sinh
    // @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/get-all-student-information")
    public ResponseEntity<?> getAllStudentInformation() {
        return studentService.findAllStudentAndInformation();
    }

    // lay ra chi tiet thong tin cua sinh vien
    // @PreAuthorize("hasAuthority('admin') or hasAuthority('teacher')")
    @GetMapping("/get-student-information")
    public ResponseEntity<?> getStudentInformationById(@RequestParam(name = "studentId")Long studentId) {
        return studentService.findStudentAndInformationById(studentId);
    }

    // @PreAuthorize("hasAuthority('admin')")
    @PostMapping("/add-students-to-classroom")
    public ResponseEntity<?> addStudentsToClassroom(@RequestParam(name = "course")Long courseId, @RequestBody AddStudents_ClassroomRequest addStudents_classroomRequest) {
        return studentService.addStudentsToClassroom(courseId,addStudents_classroomRequest);
    }

    // xem tat hoc sinh da dang ky khoa hoc
    // @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/get-all-student-register-course")
    public ResponseEntity<?> getAllStudentRegisterNewCourse() {
        return studentService.getAllStudentRegisterNewCourse();
    }

    // xem tat ca hoc sinh da dang ky mon hoc da duoc duyet
    // @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/get-all-student-with-course")
    public ResponseEntity<?> getAllStudentWithCourse() {
        return studentService.getAllStudentWithCourse();
    }

    // học sinh đăng ký
    // @PreAuthorize("hasAuthority('student')")
    @PostMapping("/register-new-course")
    public ResponseEntity<?>studentRegisterNewCourse(@RequestParam("student") Long studentId, @RequestBody RegisterStudent_CourseCommand registerStudent_courseCommand){
        return studentService.studentRegisterNewCourse(studentId, registerStudent_courseCommand);
    }

    // admin duyet khoa hoc
    // @PreAuthorize("hasAuthority('admin')")
    @PutMapping("/approved-student-register-new-course")
    public ResponseEntity<?>approvedCourseforStudent(@RequestParam("student") Long studentId, @RequestParam("course") Long courseId, @RequestBody RegisterStudent_CourseCommand registerStudent_courseCommand){
        return studentService.approvedCourseforStudent(studentId, courseId, registerStudent_courseCommand);
    }

    @PostMapping("/add-subject-with-student")
    public ResponseEntity<?>addSubjectWithStudent(@RequestParam("course") Long courseId, @RequestParam("classroom") Long classroomId){
        return studentService.addSubjectWithStudent(courseId, classroomId);
    }

    @PostMapping("/send-file")
    public Account_Attachment sendFile(@RequestParam("student") Long id, @RequestParam("file") MultipartFile file) throws Exception {
        return studentService.sendFile(id, file);
    }
}

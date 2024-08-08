package com.example.fai_edunext.controller;

import com.example.fai_edunext.dto.request.AddTeacher_ClassroomsRequest;
import com.example.fai_edunext.dto.request.TeacherDetailInformationRequest;
import com.example.fai_edunext.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {
    @Autowired
    TeacherService teacherService;

    // @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/get-all-teacher")
    public ResponseEntity<?> getAllTeacher(){
        return teacherService.findAllTeacher();
    }

    // @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/get-all-teacher-for-classroom")
    public ResponseEntity<?> showAllTeacherForClassrooms(){
        return teacherService.showTeacherForClassrooms();
    }

    // @PreAuthorize("hasAuthority('admin') or hasAuthority('teacher')")
    @GetMapping("/get-teacher-for-classroom")
    public ResponseEntity<?> getTeacherForClassrooms(@RequestParam(name = "teacherId") Long id){
        return teacherService.getTeacherToClassroomByTeacherId(id);
    }

    // @PreAuthorize("hasAuthority('admin')")
    @PostMapping("/add-teacher-to-classrooms")
    public ResponseEntity<?> addTeacherToClassrooms(@RequestBody AddTeacher_ClassroomsRequest addTeacher_classroomsRequest){
        return teacherService.addTeacherToClassrooms(addTeacher_classroomsRequest);
    }

    // Xem chi tiet thong tin teacher
    // @PreAuthorize("hasAuthority('admin') or hasAuthority('teacher')")
    @GetMapping("/get-teacher-detail")
    public ResponseEntity<?> getTeacherDetail(@RequestParam(name = "teacher") Long id){
        return teacherService.getTeacherDetailById(id);
    }

    // @PreAuthorize("hasAuthority('admin')")
    @PostMapping("/add-teacher-information")
    public ResponseEntity<?> addInformationTeacherDetail(@RequestParam(name = "teacher") Long id, @RequestBody TeacherDetailInformationRequest teacherDetailInformationRequest){
        return teacherService.addTeacherInformation(id, teacherDetailInformationRequest);
    }

    // @PreAuthorize("hasAuthority('admin') or hasAuthority('teacher')")
    @PostMapping("/update-teacher-information")
    public ResponseEntity<?> updateInformationTeacherDetail(@RequestParam(name = "teacher") Long id, @RequestBody TeacherDetailInformationRequest teacherDetailInformationRequest){
        return teacherService.updateTeacherInformation(id, teacherDetailInformationRequest);
    }
}

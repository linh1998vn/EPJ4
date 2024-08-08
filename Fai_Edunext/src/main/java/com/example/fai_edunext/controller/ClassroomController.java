package com.example.fai_edunext.controller;

import com.example.fai_edunext.dto.request.*;
import com.example.fai_edunext.services.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/classroom")
public class ClassroomController {
    @Autowired
    ClassroomService classroomService;

    // hiển thị tất cả các lớp học
    // @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/get-all-classroom")
    public ResponseEntity<?> getAllClassrooms(){
        return classroomService.showAllClassrooms();
    }

    // hiển thị lớp có sinh viên
    // @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/get-all-classroom-have-student-studying")
    public ResponseEntity<?> getAllClassHavingStudent(){
        return classroomService.showClassroomHaveStudentStudying();
    }

    // tạo 1 lớp học
    // @PreAuthorize("hasAuthority('admin')")
    @PostMapping("/create-classroom")
    public ResponseEntity<?> createClassroom(@Validated @RequestBody ClassroomRequest classroomRequest){
        return classroomService.createClassroom(classroomRequest);
    }

    // tạo 1 lớp học
    // @PreAuthorize("hasAuthority('admin')")
    @PostMapping("/update-classroom")
    public ResponseEntity<?> updateClassroom(@Validated @RequestParam(name = "classroomId") Long id,  @RequestBody ClassroomRequest classroomRequest){
        return classroomService.updateClassroom(id, classroomRequest);
    }

    // @PreAuthorize("hasAuthority('admin')")
    @DeleteMapping("/delete-classroom")
    public ResponseEntity<?> deleteClassroomById(@Validated @RequestParam(name = "classroomId") Long id){
        return classroomService.deleteClassroomById(id);
    }

    // @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/search")
    public ResponseEntity<?> searchClassroom(@RequestParam(name = "query") String query){
        return classroomService.searchClassroom(query);
    }

    // @PreAuthorize("hasAuthority('teacher')")
    @GetMapping("/get-attend-classroom")
    public ResponseEntity<?> getAttendClassroom(@RequestParam(name = "classroom") Long id){
        return classroomService.getAttendClassTeacherStudent(id);
    }


    // @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/get-classroom-detail-with-course")
    public ResponseEntity<?> getAllClassWithCouse(@RequestParam("classroom") Long id){
        return classroomService.findClassroomDetailWithCourse(id);
    }

    // @PreAuthorize("hasAuthority('teacher')")
    @PostMapping("/create-group-for-classroom")
    public ResponseEntity<?> addGroupClassToStudent(@RequestParam(name = "classroom") Long id, @RequestBody GroupRequest groupRequest){
        return classroomService.createGroupForClass(id, groupRequest);
    }

    // @PreAuthorize("hasAuthority('teacher')")
    @PostMapping("/add-students-to-group")
    public ResponseEntity<?> addStudentToGroup(@RequestParam(name = "classroom") Long classroomId, @RequestBody AddStudents_GroupRequest addStudents_groupRequest){
        return classroomService.addStudentToGroup(classroomId, addStudents_groupRequest);
    }

    // @PreAuthorize("hasAuthority('admin') or hasAuthority('teacher')")
    @GetMapping("/get-classroom-detail-to-group-students")
    public ResponseEntity<?> getClassroomToGroupAndStudent(@RequestParam(name = "classroom") Long classroomId){
        return classroomService.getClassroomToGroupAndStudent(classroomId);
    }

    // @PreAuthorize("hasAuthority('admin') or hasAuthority('teacher')")
    @GetMapping("/get-classroom-to-group-detail-students")
    public ResponseEntity<?> getClassroomToGroupAndStudent(@RequestParam(name = "classroom") Long classroomId, @RequestParam("group") String groupName){
        return classroomService.getClassroomToGroupDetailAndStudents(classroomId, groupName);
    }

    // @PreAuthorize("hasAuthority('teacher')")
    @DeleteMapping("/remove-students-to-group")
    public ResponseEntity<?> removeStudentsToGroup(@RequestParam(name = "classroom") Long classroomId, @RequestParam("group") String groupName, @RequestParam("student") Long studentId){
        return classroomService.removeStudentsToGroup(classroomId, groupName, studentId);
    }

}

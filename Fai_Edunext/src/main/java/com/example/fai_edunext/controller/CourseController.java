package com.example.fai_edunext.controller;

import com.example.fai_edunext.dto.request.CourseRequest;
import com.example.fai_edunext.dto.request.Course_ClassroomRequest;
import com.example.fai_edunext.dto.request.Course_SubjectRequest;
import com.example.fai_edunext.dto.response.Course_SubjectResponse;
import com.example.fai_edunext.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/course")
public class CourseController {
    @Autowired
    CourseService courseService;

    // @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/get-all-courses")
    public ResponseEntity<?> getAllCourses(){
        return courseService.findAddCourses();
    }

    // @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/get-course-detail")
    public ResponseEntity<?> getCourseById(@RequestParam("course")Long id){
        return courseService.findCourseById(id);
    }

    // @PreAuthorize("hasAuthority('admin')")
    @PostMapping("/create-course")
    public ResponseEntity<?> saveCourse(@RequestBody CourseRequest courseRequest){
        return courseService.createCourse(courseRequest);
    }

    // @PreAuthorize("hasAuthority('admin')")
    @PutMapping("/update-course")
    public ResponseEntity<?> saveCourse(@RequestParam("course") Long id, @RequestBody CourseRequest courseRequest){
        return courseService.updateCourse(id, courseRequest);
    }

    // @PreAuthorize("hasAuthority('admin')")
    @PutMapping("/delete-course")
    public ResponseEntity<?> saveCourse(@RequestParam("course") Long id){
        return courseService.deleteCourse(id);
    }

    // them mon hoc cho khoa hoc
    // @PreAuthorize("hasAuthority('admin')")
    @PostMapping("/add-subjects-for-course")
    public ResponseEntity<?> addSubjectToCourse(@RequestParam("course") Long id, @RequestBody Course_SubjectRequest course_subjectRequest){
        return courseService.addSubjectsToCourse(id, course_subjectRequest);
    }

    // @PreAuthorize("hasAuthority('admin') or hasAuthority('teacher')")
    @GetMapping("/get-course-detail-with-subjects")
    public ResponseEntity<?> showCourseWithSubjects(@RequestParam("course") Long id){
        return courseService.findCourseWithSubjects(id);
    }

    // @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/get-course-detail-with-classrooms")
    public ResponseEntity<?> getCourseDetailToClassroom(@RequestParam("course") Long id){
        return courseService.findCourseByIdShowClassrooms(id);
    }

    // @PreAuthorize("hasAuthority('admin')")
    @PostMapping("/add-course-for-classroom")
    public ResponseEntity<?> addCourseToClassroom(@RequestParam("course")Long courseId, @RequestBody Course_ClassroomRequest course_classroomRequest){
        return courseService.addClassroomforCourse(courseId, course_classroomRequest);
    }

    // @PreAuthorize("hasAuthority('admin')")
    @PutMapping("/update-course-for-classroom")
    public ResponseEntity<?> updateCourse_Classroom(@RequestParam("course")Long courseId, @RequestParam("classroom") Long classroomId,@RequestBody Course_ClassroomRequest course_classroomRequest){
        return courseService.updateCourse_Classroom(courseId, classroomId, course_classroomRequest);
    }

    @PostMapping("/add-student-with-subject")
    public ResponseEntity<?> addStudentWithSubject(@RequestParam("course") Long courseId, @RequestParam("classroom") Long classroomId){
        return courseService.addStudentWithSubject(courseId, classroomId);
    }
}

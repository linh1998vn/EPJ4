package com.example.fai_edunext.services;

import com.example.fai_edunext.dto.request.*;
import com.example.fai_edunext.dto.response.Course_ClassroomResponse;
import com.example.fai_edunext.dto.response.Course_SubjectResponse;
import com.example.fai_edunext.dto.response.MessageResponse;
import com.example.fai_edunext.entity.Classroom;
import com.example.fai_edunext.entity.Course;
import com.example.fai_edunext.entity.Subject;
import com.example.fai_edunext.entity.relationship.*;
import com.example.fai_edunext.repository.IAccountRepository;
import com.example.fai_edunext.repository.IClassroomRepository;
import com.example.fai_edunext.repository.ICourseRepository;
import com.example.fai_edunext.repository.ISubjectRepository;
import com.example.fai_edunext.repository.relationship_Repository.IAccount_ClassroomRepository;
import com.example.fai_edunext.repository.relationship_Repository.IAccount_SubjectRepository;
import com.example.fai_edunext.repository.relationship_Repository.ICourse_ClassroomRepository;
import com.example.fai_edunext.repository.relationship_Repository.ICourse_SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    ICourseRepository iCourseRepository;

    @Autowired
    ISubjectRepository iSubjectRepository;

    @Autowired
    ICourse_SubjectRepository iCourse_subjectRepository;

    @Autowired
    ICourse_ClassroomRepository iCourse_classroomRepository;

    @Autowired
    IClassroomRepository iClassroomRepository;

    @Autowired
    IAccount_ClassroomRepository iAccount_classroomRepository;

    @Autowired
    IAccountRepository iAccountRepository;

    @Autowired
    IAccount_SubjectRepository iAccount_subjectRepository;

    public ResponseEntity<?> findAddCourses() {
        var courses = iCourseRepository.findAll();
        if (Objects.isNull(courses)) {
            return ResponseEntity.badRequest().body(new MessageResponse("Courses not record"));
        }
        return ResponseEntity.ok().body(courses);
    }

    public ResponseEntity<?> findCourseById(Long id) {
        Optional<Course> optional = iCourseRepository.findById(id);
        if (optional.isPresent()) {
            Course course = optional.get();
            return ResponseEntity.ok().body(course);
        }
        return ResponseEntity.badRequest().body(new MessageResponse("course not exists"));
    }

    // Tạo Khóa học mới
    public ResponseEntity<?> createCourse(CourseRequest courseRequest) {
        Course course = new Course();
        course.setName(courseRequest.getName());
        if (iCourseRepository.existsByName(courseRequest.getName())) {
            return ResponseEntity.badRequest().body(new MessageResponse("course name already exists"));
        }
        course.setCode(courseRequest.getCode());
        if (iCourseRepository.existsByCode(courseRequest.getCode())) {
            return ResponseEntity.badRequest().body(new MessageResponse("course code already exists"));
        }

        course.setTime(courseRequest.getTime());
        course.setDescription(courseRequest.getDescription());
        iCourseRepository.save(course);
        return ResponseEntity.ok().body(new MessageResponse("create course successfully"));
    }

    // sửa khóa học theo id
    public ResponseEntity<?> updateCourse(Long id, CourseRequest courseRequest) {
        var find_course = iCourseRepository.findById(id);
        if (find_course.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("course not exists"));
        }
        Course course = find_course.get();
        course.setName(courseRequest.getName());
        if (courseRequest.getName() == null) {
            course.setName(course.getName());
        } else {
            if (iCourseRepository.existsByName(courseRequest.getName())) {
                return ResponseEntity.badRequest().body(new MessageResponse("course name already exists"));
            }
            course.setName(courseRequest.getName());
        }
        if (courseRequest.getCode() == null) {
            course.setCode(course.getCode());
        } else {
            if (iCourseRepository.existsByCode(courseRequest.getCode())) {
                return ResponseEntity.badRequest().body(new MessageResponse("course name already exists"));
            }
            course.setCode(courseRequest.getCode());
        }

        course.setTime(courseRequest.getTime());
        course.setDescription(courseRequest.getDescription());
        iCourseRepository.save(course);
        return ResponseEntity.ok().body(new MessageResponse("update course successfully"));


    }

    public ResponseEntity<?> deleteCourse(Long id) {
        var find_course = iCourseRepository.findById(id);
        if (find_course.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("course not exists"));
        }
        Course course = find_course.get();
        iCourseRepository.deleteById(course.getId());
        return ResponseEntity.ok().body(new MessageResponse("delete course successfully"));
    }

    // thêm môn học vào khóa học
    public ResponseEntity<?> addSubjectsToCourse(Long id, Course_SubjectRequest course_subjectRequest) {
        var find_course = iCourseRepository.findById(id);
        if (find_course.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("course not exists"));
        }
        Course course = find_course.get();
        List<SubjectRequest> subjectRequests = course_subjectRequest.getSubjects();
        for (SubjectRequest subjectRequest : subjectRequests) {
            Course_Subject course_subject = new Course_Subject();
            course_subject.setCourseId(course.getId());

            var find_subject = iSubjectRepository.findById(subjectRequest.getSubjectId());
            if (find_subject.isEmpty()) {
                return ResponseEntity.badRequest().body(new MessageResponse("subject " + subjectRequest.getSubjectId() + " not exists"));
            }
            Subject subject = find_subject.get();
            if (iCourse_subjectRepository.existsCourse_SubjectBySubjectId(subject.getId())) {
                return ResponseEntity.badRequest().body(new MessageResponse("subject " + subject.getId() + " already exists in the course"));
            }
            course_subject.setSubjectId(subject.getId());
            iCourse_subjectRepository.save(course_subject);
        }
        return ResponseEntity.ok().body(new MessageResponse("add subjects to course successfully"));
    }

    // hiển thị khóa học và danh sách môn học trong khóa học
    public ResponseEntity<?> findCourseWithSubjects(Long courseId) {
        var find_course = iCourseRepository.findById(courseId);
        if (find_course.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("course not exists"));
        }
        Course course = find_course.get();
        var courses = iCourse_subjectRepository.findCourse_SubjectsByCourseId(course.getId());
        List<SubjectRequest> subjectRequests = new ArrayList<>();
        Course_SubjectResponse course_subjectResponse = new Course_SubjectResponse();
        for (Course_Subject course_subject : courses) {
            course_subjectResponse.setCourse_name(course.getName());

            var subject = iSubjectRepository.findById(course_subject.getSubjectId()).get();
            SubjectRequest subjectRequest = new SubjectRequest(subject);
            subjectRequests.add(subjectRequest);
            course_subjectResponse.setSubjects(subjectRequests);
        }
        return ResponseEntity.ok().body(course_subjectResponse);

    }

    /*
        - tìm course có tồn tại không?
        - tìm ra danh sách lớp học từ course
     */
    public ResponseEntity<?> findCourseByIdShowClassrooms(Long id) {
        var find_course = iCourseRepository.findById(id);
        if (find_course.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("course not exists"));
        }
        Course course = find_course.get();
        Course_ClassroomResponse course_classroomResponse = new Course_ClassroomResponse();
        List<ClassroomRequest> classroomRequests = new ArrayList<>();
        var find_course_classrooms = iCourse_classroomRepository.findCourse_ClassroomsByCourseId(course.getId());
        for (Course_Classroom course_classroom : find_course_classrooms) {
            course_classroomResponse.setCourseName(course.getName());
            course_classroomResponse.setCode(course.getCode());

            var find_classroom = iClassroomRepository.findById(course_classroom.getClassroomId());
            Classroom classroom = find_classroom.get();
            ClassroomRequest classroomRequest = new ClassroomRequest(classroom);
            classroomRequests.add(classroomRequest);
        }
        course_classroomResponse.setClassrooms(classroomRequests);
        return ResponseEntity.ok().body(course_classroomResponse);
    }

    // check xem Nếu khóa học mà thời gian vẫn diễn ra thì sẽ không add được vào lớp học ( xong )
    // setStatus = để mặc định enable .
    // bh khóa học xong thì sẽ chuyển sang Disable
    public ResponseEntity<?> addClassroomforCourse(Long courseId, Course_ClassroomRequest course_classroomRequest) {
        var find_course = iCourseRepository.findById(courseId);
        if (find_course.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("classroom not exists"));
        }

        Course_Classroom course_classroom = new Course_Classroom();
        course_classroom.setCourseId(find_course.get().getId());

        var find_classroom = iClassroomRepository.findById(course_classroomRequest.getClassroomId());
        var course_classrooms = iCourse_classroomRepository.findAll();

        if (find_classroom.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("course not exists"));
        }

        if (course_classrooms.size() == 0) {
            course_classroom.setClassroomId(find_classroom.get().getId());
            course_classroom.setStatus(course_classroomRequest.getStatus());
            iCourse_classroomRepository.save(course_classroom);
        } else {
            for (Course_Classroom findCourse_Classroom : course_classrooms) {

                course_classroom.setClassroomId(find_classroom.get().getId());
                course_classroom.setStatus(course_classroomRequest.getStatus());

                if (iCourse_classroomRepository.existsCourse_ClassroomByClassroomId(findCourse_Classroom.getClassroomId()) && iCourse_classroomRepository.existsCourse_ClassroomByCourseId(findCourse_Classroom.getId())) {
                    return ResponseEntity.badRequest().body(new MessageResponse("this course already exists in the classroom"));
                }

                iCourse_classroomRepository.save(course_classroom);
            }
        }
        return ResponseEntity.ok().body(new MessageResponse("add classroom to course successfully"));
    }

    public ResponseEntity<?> updateCourse_Classroom(Long courseId, Long classroomId, Course_ClassroomRequest course_classroomRequest) {
        var find_course = iCourseRepository.findById(courseId);
        if (find_course.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("course not exists"));
        }
        Course course = find_course.get();

        var find_classrooms = iCourse_classroomRepository.findCourse_ClassroomsByCourseId(course.getId());
        for (Course_Classroom check_course_classroom : find_classrooms) {
            var check_class = iClassroomRepository.findById(check_course_classroom.getClassroomId()).get();
            var find_classroom = iClassroomRepository.findById(classroomId);

            if (find_classrooms.isEmpty()) {
                return ResponseEntity.badRequest().body(new MessageResponse("classroom not exists"));
            }
            if (check_class.getId().equals(find_classroom.get().getId())) {
                check_course_classroom.setCourseId(check_course_classroom.getCourseId());
                check_course_classroom.setClassroomId(check_course_classroom.getClassroomId());
                check_course_classroom.setStatus(course_classroomRequest.getStatus());
                iCourse_classroomRepository.save(check_course_classroom);
            }
        }
        return ResponseEntity.ok().body(new MessageResponse("update status course successfully"));
    }

    // trùng code
    public ResponseEntity<?> addStudentWithSubject(Long courseId, Long classroomId) {
        var find_course = iCourseRepository.findById(courseId);
        if (find_course.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("course not exists"));
        }

        Account_Subject account_subject = new Account_Subject();
        var find_course_classrooms = iCourse_classroomRepository.findCourse_ClassroomsByCourseId(find_course.get().getId());
        var find_course_subjects = iCourse_subjectRepository.findCourse_SubjectsByCourseId(find_course.get().getId());
        for (Course_Classroom course_classroom : find_course_classrooms) {
            var check_classroom = iClassroomRepository.findById(course_classroom.getClassroomId()).get();
            var find_classroom_request = iClassroomRepository.findById(classroomId);
            if (find_classroom_request.isEmpty()) {
                return ResponseEntity.badRequest().body(new MessageResponse("classroom not exists"));
            }

            var find_classroom_accounts = iAccount_classroomRepository.findAccount_ClassroomsByClassroomId(find_classroom_request.get().getId());
            if (check_classroom.getId().equals(find_classroom_request.get().getId())) {
                List<Account_Subject> account_subjects = new ArrayList<>();
                // tim ra danh sach mon hoc
                for (Course_Subject course_subject : find_course_subjects) {
                    var check_subject = iSubjectRepository.findById(course_subject.getSubjectId()).get();
                    account_subject.setSubjectId(check_subject.getId());

                    // tim ra danh sach sinh vien
                }
                for (Account_Classroom account_classroom : find_classroom_accounts) {
                    var check_student = iAccountRepository.findById(account_classroom.getAccountId()).get();
                    account_subject.setAccountId(check_student.getId());

                }
                iAccount_subjectRepository.save(account_subject);
            }
        }
        return ResponseEntity.ok().body(new MessageResponse("test successfully"));
    }
}

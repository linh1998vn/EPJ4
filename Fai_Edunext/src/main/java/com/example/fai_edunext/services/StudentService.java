package com.example.fai_edunext.services;

import com.example.fai_edunext.command.RegisterStudent_CourseCommand;
import com.example.fai_edunext.dto.request.CourseRequest;
import com.example.fai_edunext.dto.request.StudentRequest;
import com.example.fai_edunext.dto.request.AddStudents_ClassroomRequest;
import com.example.fai_edunext.dto.request.UserRequest;
import com.example.fai_edunext.dto.response.MessageResponse;
import com.example.fai_edunext.dto.response.StudentInformationResponse;
import com.example.fai_edunext.dto.response.Student_CourseResponse;
import com.example.fai_edunext.entity.*;
import com.example.fai_edunext.entity.relationship.*;
import com.example.fai_edunext.repository.*;
import com.example.fai_edunext.repository.relationship_Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class StudentService {

    @Autowired
    IAccount_ClassroomRepository iAccount_classroomRepository;

    @Autowired
    IAccountRepository iAccountRepository;

    @Autowired
    IClassroomRepository iClassroomRepository;

    @Autowired
    IAccount_UserRepository iAccount_userRepository;

    @Autowired
    IUserRepository iUserRepository;

    @Autowired
    ICourseRepository iCourseRepository;

    @Autowired
    IAccount_CourseRepository iAccount_courseRepository;

    @Autowired
    IAttachmentRepository iAttachmentRepository;

    @Autowired
    IAccount_AttachmentRepository iAccount_attachmentRepository;

    @Autowired
    ICourse_ClassroomRepository iCourse_classroomRepository;

    @Autowired
    ICourse_SubjectRepository iCourse_subjectRepository;

    @Autowired
    IAccount_SubjectRepository iAccount_subjectRepository;

    @Autowired
    ISubjectRepository iSubjectRepository;

    public ResponseEntity<?> getAllStudent() {
        List<User> approveStudents = iUserRepository.findAllStudentApprove();
        if (approveStudents.size() == 0) {
            return ResponseEntity.badRequest().body(new MessageResponse("student not record"));
        }
        return ResponseEntity.ok().body(approveStudents);
    }

    public ResponseEntity<?> findAllStudentAndInformation() {
        List<Account_User> account_users = iAccount_userRepository.findAll();
        if (account_users.size() == 0) {
            return ResponseEntity.badRequest().body(new MessageResponse("Student not record"));
        } else {
            List<StudentInformationResponse> studentInformationResponses = new ArrayList<>();
            StudentInformationResponse studentInformationResponse = new StudentInformationResponse();
            for (Account_User account_user : account_users) {
                var checkAccount = iAccountRepository.findById(account_user.getAccountId()).get();
                StudentRequest studentRequest = new StudentRequest(checkAccount);
//                Account account = new Account();
                studentInformationResponse.setStudent(studentRequest);

                var CheckInformation = iUserRepository.findById(account_user.getUserId()).get();
                UserRequest userRequest = new UserRequest(CheckInformation);
                studentInformationResponse.setInformation(userRequest);
                studentInformationResponses.add(studentInformationResponse);
            }
            return ResponseEntity.ok().body(studentInformationResponses);
        }
    }

    public ResponseEntity<?> findStudentAndInformationById(Long studentId) {
        var find_account_user = iAccount_userRepository.findAccount_UserByAccountId(studentId);
        if (find_account_user.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Student not exists"));
        }
        Account_User account_user = find_account_user.get();
        StudentInformationResponse studentInformationResponse = new StudentInformationResponse();
        var checkAccount = iAccountRepository.findById(account_user.getAccountId()).get();
        StudentRequest studentRequest = new StudentRequest(checkAccount);
        studentInformationResponse.setStudent(studentRequest);

        var CheckInformation = iUserRepository.findById(account_user.getUserId()).get();
        UserRequest userRequest = new UserRequest(CheckInformation);
        studentInformationResponse.setInformation(userRequest);
        return ResponseEntity.ok().body(studentInformationResponse);
    }

    // add sinh viên vào lớp học . Một lớp chỉ được thêm 25 học sinh
    public ResponseEntity<?> addStudentsToClassroom(Long courseId, AddStudents_ClassroomRequest addStudents_classroomRequest) {
        var find_course = iCourseRepository.findById(courseId);
        if (find_course.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("course not exists"));
        }
        var find_course_classrooms = iCourse_classroomRepository.findCourse_ClassroomsByCourseId(find_course.get().getId());

        for (Course_Classroom course_classroom : find_course_classrooms) {
            var find_classroom = iClassroomRepository.findById(addStudents_classroomRequest.getClassroomId());
            if (find_classroom.isEmpty()) {
                return ResponseEntity.badRequest().body(new MessageResponse("Classroom not exists"));
            }

            var find_account_courses = iAccount_courseRepository.findAccount_CoursesByCourseId(find_course.get().getId());
            for (Account_Course account_course : find_account_courses) {

                List<StudentRequest> studentRequests = addStudents_classroomRequest.getStudents();
                for (StudentRequest studentRequest : studentRequests) {
                    var find_student_request = iAccountRepository.findById(studentRequest.getStudentId());
                    if (find_student_request.isEmpty()) {
                        return ResponseEntity.badRequest().body(new MessageResponse("This student has not registered for the course"));
                    }

                    if (studentRequests.size() >= 25) {
                        return ResponseEntity.badRequest().body(new MessageResponse("The class is full, cannot add students to the class"));
                    }
                    var check_account = iAccount_courseRepository.findAccount_CourseByAccountId(find_student_request.get().getId());
                    if (check_account.get().getStatus().equals("waiting")) {
                        return ResponseEntity.badRequest().body(new MessageResponse("This student has not registered for the course"));
                    }

                    if (iAccount_classroomRepository.existsAccount_ClassroomByClassroomId(find_classroom.get().getId()) && iAccount_classroomRepository.existsAccount_ClassroomByAccountId(find_student_request.get().getId())) {
                        return ResponseEntity.badRequest().body(new MessageResponse("this studetn already exists in the classroom"));
                    }

                    Account_Classroom account_classroom = new Account_Classroom();
                    if (course_classroom.getCourseId().equals(find_classroom.get().getId())) {
                        account_classroom.setClassroomId(find_classroom.get().getId());
                    }
                    if (account_course.getAccountId().equals(find_student_request.get().getId())) {
                        account_classroom.setAccountId(find_student_request.get().getId());
                        iAccount_classroomRepository.save(account_classroom);
                    }
                }
            }
            return ResponseEntity.ok().body(new MessageResponse("add account student to classroom successfully"));
        }
        return ResponseEntity.ok().body(new MessageResponse("add account student to classroom successfully"));
    }

    // học sinh đăng ký 1 khóa học mới
    public ResponseEntity<?> studentRegisterNewCourse(Long studentId, RegisterStudent_CourseCommand registerStudent_courseCommand) {
        var find_student = iAccountRepository.findById(studentId);
        if (find_student.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("student not exists"));
        }
        Account account = find_student.get();
        if (account.getPosition().equals("teacher") && account.getPosition().equals("admin")) {
            return ResponseEntity.badRequest().body(new MessageResponse("this account is not a student"));
        }
        var find_course = iCourseRepository.findById(registerStudent_courseCommand.getCourseId());
        if (find_course.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("course not exists"));
        }
        Course course = find_course.get();
        if (iAccount_courseRepository.existsAccount_CourseByAccountId(account.getId()) && iAccount_courseRepository.existsAccount_CourseByCourseId(course.getId())) {
            return ResponseEntity.badRequest().body(new MessageResponse("this student has taken this course"));
        }
        Account_Course account_course = new Account_Course();
        account_course.setAccountId(account.getId());
        account_course.setCourseId(course.getId());
        account_course.setStatus("waiting");
        iAccount_courseRepository.save(account_course);
        return ResponseEntity.ok().body(new MessageResponse("register course for student successfully"));
    }


    public Account_Attachment sendFile(Long id, MultipartFile file) throws Exception {
        var find_id = iAccountRepository.findById(id).get();

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (fileName.contains("..")) {
                throw new Exception("Filename contains invalid path sequence "
                        + fileName);
            }
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            String submitTime = dateFormat.format(date);

            Attachment attachment = new Attachment(fileName,
                    file.getContentType(),
                    file.getBytes(),
                    submitTime);
            iAttachmentRepository.save(attachment);

            Account_Attachment account_attachment = new Account_Attachment();
            account_attachment.setAccountId(find_id.getId());
            account_attachment.setAttachmentId(attachment.getId());
            return iAccount_attachmentRepository.save(account_attachment);
        } catch (Exception e) {
            throw new Exception("Could not save File: " + fileName);
        }
    }

    public ResponseEntity<?> getAllStudentRegisterNewCourse() {
        var find_account_courses = iAccount_courseRepository.findAllStudentRegisterCourse("waiting");
        if (find_account_courses.size() == 0) {
            return ResponseEntity.badRequest().body(new MessageResponse("Not record"));
        }

        List<Student_CourseResponse> student_courseResponses = new ArrayList<>();
        CourseRequest courseRequest = null;
        for (Account_Course ac : find_account_courses) {
            Student_CourseResponse student_courseResponse = new Student_CourseResponse();
            var check_student = iAccountRepository.findById(ac.getAccountId()).get();
            student_courseResponse.setUsername(check_student.getUsername());
            student_courseResponse.setCode(check_student.getUsername());

            var check_course = iCourseRepository.findById(ac.getCourseId()).get();
            courseRequest = new CourseRequest(check_course);
            student_courseResponse.setCourse(courseRequest);

            student_courseResponse.setStatus(ac.getStatus());
            student_courseResponses.add(student_courseResponse);
        }
        return ResponseEntity.ok().body(student_courseResponses);
    }

    public ResponseEntity<?> getAllStudentWithCourse() {
        var find_account_courses = iAccount_courseRepository.findAllStudentRegisterCourse("approved");
        if (find_account_courses.size() == 0) {
            return ResponseEntity.badRequest().body(new MessageResponse("Not record"));
        }

        List<Student_CourseResponse> student_courseResponses = new ArrayList<>();
        CourseRequest courseRequest = null;
        for (Account_Course ac : find_account_courses) {
            Student_CourseResponse student_courseResponse = new Student_CourseResponse();
            var check_student = iAccountRepository.findById(ac.getAccountId()).get();
            student_courseResponse.setUsername(check_student.getUsername());
            student_courseResponse.setCode(check_student.getUsername());

            var check_course = iCourseRepository.findById(ac.getCourseId()).get();
            courseRequest = new CourseRequest(check_course);
            student_courseResponse.setCourse(courseRequest);

            student_courseResponse.setStatus(ac.getStatus());
            student_courseResponses.add(student_courseResponse);
        }
        return ResponseEntity.ok().body(student_courseResponses);
    }

    public ResponseEntity<?> approvedCourseforStudent(Long studentId, Long courseId, RegisterStudent_CourseCommand registerStudent_courseCommand) {
        var find_student_courses = iAccount_courseRepository.findAllStudentRegisterCourse("waiting");

        if (find_student_courses.size() == 0) {
            return ResponseEntity.badRequest().body(new MessageResponse("Not record"));
        }

        for (Account_Course ac : find_student_courses) {
            var find_student = iAccountRepository.findById(studentId);
            if (find_student.isEmpty()) {
                return ResponseEntity.badRequest().body(new MessageResponse("student not exists"));
            }

            var find_course = iCourseRepository.findById(courseId);
            if (find_course.isEmpty()) {
                return ResponseEntity.badRequest().body(new MessageResponse("student not exists"));
            }

            if (ac.getAccountId().equals(find_student.get().getId()) && ac.getCourseId().equals(find_course.get().getId())) {
                ac.setAccountId(ac.getAccountId());
                ac.setCourseId(ac.getCourseId());
                ac.setStatus("approved");
                iAccount_courseRepository.save(ac);
            }
        }
        return ResponseEntity.ok().body(new MessageResponse("Confirmation of successful course registration"));

    }

    // đang bị lỗi
    public ResponseEntity<?> addSubjectWithStudent(Long courseId, Long classroomId) {
        var find_course = iCourseRepository.findById(courseId);
        if (find_course.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("course not exists"));
        }

        var find_classroom = iClassroomRepository.findById(classroomId);
        if (find_classroom.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("classroom not exists"));
        }

        if (!(iCourse_classroomRepository.existsCourse_ClassroomByCourseId(courseId) && iCourse_classroomRepository.existsCourse_ClassroomByClassroomId(classroomId))) {
            return ResponseEntity.badRequest().body(new MessageResponse("This course does not exist in class"));
        }

//        if (iCourse_classroomRepository.existsCourse_ClassroomByCourseId(courseId) && iCourse_classroomRepository.existsCourse_ClassroomByClassroomId(classroomId) || iCourse_classroomRepository.existsAccount_CourseByStatus("disable")) {
//            return ResponseEntity.badRequest().body(new MessageResponse("class not started this key"));
//        }

        var find_classroom_students = iAccount_classroomRepository.findAccount_ClassroomsByClassroomId(find_classroom.get().getId());

        if (find_classroom_students.size() == 0) {
            return ResponseEntity.badRequest().body(new MessageResponse("this student is not exists in the classroom"));
        }

        for (Account_Classroom account_classroom : find_classroom_students) {
            var find_student = iAccountRepository.findById(account_classroom.getAccountId()).get();
            // lay ra danh mon hoc
            var find_student_subjects = iAccount_subjectRepository.findAccount_SubjectsByAccountId(find_student.getId());
            // lấy ra được 1 danh sách môn học theo course
            var find_course_subjects = iCourse_subjectRepository.findCourse_SubjectsByCourseId(find_course.get().getId());
            if (find_student.getPosition().equals("student")) {
                for (Course_Subject course_subject : find_course_subjects) {
                    var find_subject = iSubjectRepository.findById(course_subject.getSubjectId()).get();
//                    if (iAccount_subjectRepository.existsAccount_SubjectByAccountId(find_student.getId()) && iAccount_subjectRepository.existsAccount_SubjectBySubjectId(find_subject.getId())){
                        Account_Subject account_subject_1 = new Account_Subject();
                        account_subject_1.setAccountId(find_student.getId());
                        account_subject_1.setSubjectId(find_subject.getId());
                        account_subject_1.setStatus(find_classroom.get().getClassroomName());
                        iAccount_subjectRepository.save(account_subject_1);
//                    }
                }
            }
        }
        return ResponseEntity.ok().body(new MessageResponse("add student with subject successfully"));
    }

    /*
        - tìm thông tin student
        - tim danh sach lop
        - add student + classroom dưới dạng chờ xếp lớp
     */
//    public ResponseEntity<?> studentRegisterForClasses(Long studentId, ClassroomRequest classroomRequest) {
//        var check_student = iAccountRepository.findById(studentId);
//        if (check_student.isEmpty()){
//            return ResponseEntity.badRequest().body(new MessageResponse("student not exists"));
//        }
//
//        var check_classrooms = iClassroomRepository.findAllClassByStatus("disabled");
//        for (Classroom classroom : check_classrooms){
//            var check_classroom = iClassroomRepository.findById(classroom.getId()).get();
//            if (check_classroom.getId().equals(classroomRequest.getClassroomId())){
//                Account_Classroom account_classroom = new Account_Classroom();
//                account_classroom.setAccountId(check_student.get().getId());
//                account_classroom.setClassroomId(classroomRequest.getClassroomId());
//                account_classroom.setStatus("");
//            }
//        }
//    }


    // Profile

//    public ResponseEntity<?> updateStudentInformation(Long id){
//        var checkStudentById = iAccount_userRepository.findById(id).get();
//        if (Objects.isNull(checkStudentById)) {
//            return ResponseEntity.badRequest().body(new MessageResponse("Student not exists"));
//        }
//        UpdateProfileCommand updateProfileCommand = new UpdateProfileCommand();
//        var checkAccount = iAccountRepository.findById(checkStudentById.getAccountId()).get();
////        if (updateProfileCommand.getOldPassword().equalsIgnoreCase(checkAccount.getPassword())){
//            checkAccount.setPassword(updateProfileCommand.getNewPassword());
////        } else
////            return ResponseEntity.badRequest().body(new MessageResponse("Update Password failed"));
//
//        var checkInformation = iUserRepository.findById(checkStudentById.getUserId()).get();
//        checkInformation.setFirstName(updateProfileCommand.getFirstName());
//        checkInformation.setLastName(updateProfileCommand.getLastName());
//        checkInformation.setPhone(updateProfileCommand.getPhone());
//        checkInformation.setBirthday(updateProfileCommand.getBirthday());
//        checkInformation.setAddress(updateProfileCommand.getAddress());
//        iUserRepository.save(checkInformation);
//        return ResponseEntity.ok().body(new MessageResponse("update student successfully"));
//    }
}

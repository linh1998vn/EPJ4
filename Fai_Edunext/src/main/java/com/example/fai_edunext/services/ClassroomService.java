package com.example.fai_edunext.services;

import com.example.fai_edunext.dto.request.*;
import com.example.fai_edunext.dto.response.*;
import com.example.fai_edunext.entity.Account;
import com.example.fai_edunext.entity.Classroom;
import com.example.fai_edunext.entity.Course;
import com.example.fai_edunext.entity.Group;
import com.example.fai_edunext.entity.relationship.Account_Classroom;
import com.example.fai_edunext.entity.relationship.Account_Group;
import com.example.fai_edunext.entity.relationship.Classroom_Group;
import com.example.fai_edunext.entity.relationship.Course_Classroom;
import com.example.fai_edunext.repository.*;
import com.example.fai_edunext.repository.relationship_Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Service
public class ClassroomService {
    @Autowired
    IClassroomRepository iClassroomRepository;

    @Autowired
    IAccountRepository iAccountRepository;

    @Autowired
    IUserRepository iUserRepository;

    @Autowired
    IAccount_ClassroomRepository iAccount_classroomRepository;

    @Autowired
    IAccount_UserRepository iAccount_userRepository;

    @Autowired
    ICourseRepository iCourseRepository;

    @Autowired
    IGroupRepository iGroupRepository;

    @Autowired
    IClassroom_GroupRepository iClassroom_groupRepository;

    @Autowired
    IAccount_GroupRepository iAccount_groupRepository;

    @Autowired
    ICourse_ClassroomRepository iCourse_classroomRepository;

    public ResponseEntity<?> showClassroomHaveStudentStudying() {
        List<Classroom> classrooms = iClassroomRepository.findAll();
        if (classrooms.size() == 0) {
            return ResponseEntity.badRequest().body("Classroom Not Record");
        } else {
            List<Classroom_StudentsResponse> classroom_studentsResponses = new ArrayList<>();
            for (Classroom classroom : classrooms) {
                Classroom_StudentsResponse classroom_studentsResponse = new Classroom_StudentsResponse();
                classroom_studentsResponse.setClassroomName(classroom.getClassroomName());

                List<Account_Classroom> account_classrooms = iAccount_classroomRepository.findAccount_ClassroomsByClassroomId(classroom.getId());
                List<StudentResponse> studentResponses = new ArrayList<>();
                for (Account_Classroom account_classroom : account_classrooms) {
                    var account = iAccountRepository.findById(account_classroom.getAccountId()).get();
//                    List<String> roleNames = checkAccount.getRoles().stream().map(Role::getName).collect(Collectors.toList());
                    if (account.getPosition().equalsIgnoreCase("student")) {
                        StudentResponse studentResponse = new StudentResponse();
                        // thong tin tu bang user
                        var check = iAccount_userRepository.findAccount_UserByAccountId(account.getId()).get();

                        var infoUser = iUserRepository.findById(check.getUserId()).get();
                        String fullName = infoUser.getLastName() + " " + infoUser.getFirstName();
                        studentResponse.setFull_name(fullName);
                        // thong tin tu bang account
                        studentResponse.setUsername(account.getUsername());
                        studentResponse.setCode(account.getCode());
                        studentResponse.setPosition(account.getPosition());
                        studentResponses.add(studentResponse);
                    }
                }
                classroom_studentsResponse.setStudents(studentResponses);
                classroom_studentsResponses.add(classroom_studentsResponse);
            }
            return ResponseEntity.ok().body(classroom_studentsResponses);
        }
    }

    public ResponseEntity<?> showAllClassrooms() {
        List<Classroom> classrooms = iClassroomRepository.findAll();
        if (classrooms.size() == 0) {
            return ResponseEntity.badRequest().body("Classroom Not Record");
        } else {
            return ResponseEntity.ok().body(classrooms);
        }
    }

    public ResponseEntity<?> createClassroom(ClassroomRequest classroomRequest) {
        Classroom classroom = new Classroom();
        classroom.setClassroomName(classroomRequest.getClassroomName());
        if (iClassroomRepository.existsByClassroomName(classroomRequest.getClassroomName())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Classroom name already exists"));
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        classroom.setCreatedDate(dateFormat.format(date));
        iClassroomRepository.save(classroom);
        return ResponseEntity.ok().body(new MessageResponse("create classroom successfully"));
    }

    // update chỉ cho update thời gian chứ không được update tên lớp
    public ResponseEntity<?> updateClassroom(Long classroomId, ClassroomRequest classroomRequest) {
        var find_classroom = iClassroomRepository.findById(classroomId);
        if (find_classroom.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Classroom Update Failed"));
        }
        Classroom classroom = find_classroom.get();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        classroom.setUpdatedDate(dateFormat.format(date));
        iClassroomRepository.save(classroom);
        return ResponseEntity.ok().body(new MessageResponse("Classroom Update Success"));
    }

    public ResponseEntity<?> deleteClassroomById(Long classroomId) {
        Optional<Classroom> optional = iClassroomRepository.findById(classroomId);
        if (optional.isPresent()) {
            iClassroomRepository.deleteById(classroomId);
            return ResponseEntity.ok().body(new MessageResponse("Classroom Delete Success"));
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Classroom Delete Failed"));
    }

    public ResponseEntity<?> searchClassroom(String query) {
        List<Classroom> classrooms = iClassroomRepository.searchClassrooms(query);
        return ResponseEntity.ok().body(classrooms);
    }


    public ResponseEntity<?> getAttendClassTeacherStudent(Long classroomId) {
        var account_classrooms = iAccount_classroomRepository.findAccount_ClassroomsByClassroomId(classroomId);
        if (Objects.isNull(account_classrooms)) {
            return ResponseEntity.badRequest().body(new MessageResponse("classroom not exists"));
        }
        Attend_Class_Teacher_StudentsResponse attend_class_teacher_studentsResponse = new Attend_Class_Teacher_StudentsResponse();
        List<StudentRequest> studentRequests = new ArrayList<>();
        for (Account_Classroom account_classroom : account_classrooms) {
            var classroom = iClassroomRepository.findById(classroomId).get();
            attend_class_teacher_studentsResponse.setClassName(classroom.getClassroomName());

            var account = iAccountRepository.findById(account_classroom.getAccountId()).get();
            if (account.getPosition().equalsIgnoreCase("teacher")) {
                TeacherRequest teacherRequest = new TeacherRequest(account);
                attend_class_teacher_studentsResponse.setTeacher(teacherRequest);
            } else {
                StudentRequest studentRequest = new StudentRequest(account);
                studentRequests.add(studentRequest);
                attend_class_teacher_studentsResponse.setStudents(studentRequests);
            }

        }
        return ResponseEntity.ok().body(attend_class_teacher_studentsResponse);
    }

    public ResponseEntity<?> findClassroomDetailWithCourse(Long id) {
        Optional<Classroom> optional = iClassroomRepository.findById(id);
        if (optional.isPresent()) {
            Classroom classroom = optional.get();
            Classroom_CourseResponse _classroomCourseResponse = new Classroom_CourseResponse();
            _classroomCourseResponse.setClassroomName(classroom.getClassroomName());

            List<CourseRequest> courseRequests = new ArrayList<>();
            var checkCourse_Classrooms = iCourse_classroomRepository.findCourse_ClassroomsByClassroomId(classroom.getId());

            for (Course_Classroom course_classroom : checkCourse_Classrooms) {
                var course = iCourseRepository.findById(course_classroom.getCourseId()).get();
//                Course course = new Course();
                CourseRequest courseRequest = new CourseRequest(course);
                courseRequests.add(courseRequest);
            }
            _classroomCourseResponse.setCourses(courseRequests);
            return ResponseEntity.ok().body(_classroomCourseResponse);
        }
        return ResponseEntity.badRequest().body(new MessageResponse("classroom not exists"));
    }

    public ResponseEntity<?> createGroupForClass(Long id, GroupRequest groupRequest) {
        Optional<Classroom> optional = iClassroomRepository.findById(id);
        if (optional.isPresent()) {
            Classroom classroom = optional.get();

            Classroom_Group classroom_group = new Classroom_Group();
            classroom_group.setClassroomId(classroom.getId());

            Group group = new Group();
            group.setGroupName(classroom.getClassroomName() + "_" + groupRequest.getName());
            if (iGroupRepository.existsByGroupName(group.getGroupName())) {
                return ResponseEntity.badRequest().body(new MessageResponse("group " + classroom.getClassroomName() + "_" + groupRequest.getName() + " already exists in the classroom"));
            }
            group.setStatus(groupRequest.getStatus());
            iGroupRepository.save(group);

            classroom_group.setGroupId(group.getId());
            iClassroom_groupRepository.save(classroom_group);
            return ResponseEntity.ok().body(new MessageResponse("Create Group for classroom successfully"));
        }
        return ResponseEntity.badRequest().body(new MessageResponse("classroom not exists"));
    }

    // từ lớp học sẽ lấy ra được danh sách group Id OK
    // check xem group Id từ request gửi lên có trùng trong lớp học hay không? OK
    // trùng    | + B1: trước tiên ta phải lấy ra được danh sách học sinh từ lớp OK
    //          | + b2: ta sẽ gửi request gorup Id lên => kiểm tra request gorup Id có trong danh sách groupId OK
    //          | + b3: tạo 1 List<StudentRequest> studentRequests = addStudents_groupRequest.getStudents;
    //          | + b4: duyệt danh sách gửi lên từ request student
    //          | + b5: làm sao để check xem student có tồn tại trong B1 hay không .
    //          | + b6: Nếu có tồn tại thì sẽ list.add(x)
    //          | + B7: lưu vào db

    public ResponseEntity<?> addStudentToGroup(Long classroomId, AddStudents_GroupRequest addStudents_groupRequest) {
        var check_Classroom = iClassroomRepository.findById(classroomId);
        if (check_Classroom.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("classroom not exists"));
        }
        Classroom classroom = check_Classroom.get();

        var checkClassroom_Groups = iClassroom_groupRepository.findClassroom_GroupsByClassroomId(classroom.getId());
        // lấy ra danh sách groupId
        for (Classroom_Group classroom_group : checkClassroom_Groups) {
            Optional<Group> check_Group = iGroupRepository.findById(classroom_group.getGroupId());
            if (check_Classroom.isEmpty()) {
                return ResponseEntity.badRequest().body(new MessageResponse("group not exists"));
            }
            Group group = check_Group.get();
            var check_Accounts_Classroom = iAccount_classroomRepository.findAccount_ClassroomsByClassroomId(classroom.getId());
            for (Account_Classroom account_classroom : check_Accounts_Classroom) {
                var check_account_classroom = iAccountRepository.findById(account_classroom.getAccountId());
                List<StudentRequest> studentRequests = addStudents_groupRequest.getStudents();

                for (StudentRequest studentRequest : studentRequests) {
                    if (!group.getId().equals(addStudents_groupRequest.getGroupId())) {
                        return ResponseEntity.badRequest().body(new MessageResponse("group not exists in the classroom"));
                    }

                    // làm đến đây là oke rồi nè (*_*)
                    Optional<Account> check_student_request = iAccountRepository.findById(studentRequest.getStudentId());

                    if (check_student_request.isEmpty()) {
                        return ResponseEntity.badRequest().body(new MessageResponse("student request not exists in the classroom"));
                    }
                    Account account_Request = check_student_request.get();
                    if (account_Request.getPosition().equalsIgnoreCase("teacher")) {
                        return ResponseEntity.badRequest().body(new MessageResponse("this is not a student account"));
                    }
                    var check_Group_Students = iAccount_groupRepository.findAccount_GroupsByGroupId(addStudents_groupRequest.getGroupId());
                    for (Account_Group account_group : check_Group_Students) {
                        if (iAccount_groupRepository.existsAccount_GroupByAccountId(account_group.getGroupId()) && iAccount_groupRepository.existsAccount_GroupByAccountId(account_group.getAccountId())) {
                            return ResponseEntity.badRequest().body(new MessageResponse("student already exists in the group"));
                        }
                    }
                    if (check_Group_Students.size() > 6) {
                        return ResponseEntity.badRequest().body(new MessageResponse("group is full. Please add to another group"));
                    }
                    if (account_Request.getId().equals(check_account_classroom.get().getId())) {
                        Account_Group account_group = new Account_Group();
                        account_group.setGroupId(addStudents_groupRequest.getGroupId());
                        account_group.setAccountId(account_Request.getId());
                        iAccount_groupRepository.save(account_group);
                    }
                }
            }
            return ResponseEntity.ok().body("add students to success group");
        }
        return ResponseEntity.badRequest().body(new MessageResponse("classroom not exists"));
    }

    // tu lop hoc ID lay duoc danh sach group ID,
    // tu group ID lay danh sach thong tin hoc sinh.
    // return (groupID, va danh sach thuoc group nay)

    // group 1 co danh sach sinh vien 1
    // group 2 co danh sach sinh vien 2
    // lay danh sach group theo classrooom ID, tra ra ID group
    // duyet danh sach groupID thi ta lay, danh sach sinh vien tuong uong voi group ID.
    // tra ra
    // ten lop
    //  ten nhom
    //      List<Student>
    //

    public ResponseEntity<?> getClassroomToGroupAndStudent(Long classroomId) {
        Optional<Classroom> optional_Classroom = iClassroomRepository.findById(classroomId);
        if (optional_Classroom.isPresent()) {
            Classroom classroom = optional_Classroom.get();
            var checkClassroom_Groups = iClassroom_groupRepository.findClassroom_GroupsByClassroomId(classroom.getId());

            List<Classroom_Group_StudentResponse> classroom_group_studentResponses = new ArrayList<>();
            List<Group_StudentResponse> group_studentResponses = new ArrayList<>();

            Classroom_Group_StudentResponse classroom_group_studentResponse = new Classroom_Group_StudentResponse();
            classroom_group_studentResponse.setClassroomName(classroom.getClassroomName());

            for (Classroom_Group classroom_group : checkClassroom_Groups) {
                var group = iGroupRepository.findById(classroom_group.getGroupId()).get();
                Group_StudentResponse group_studentResponse = new Group_StudentResponse();
                group_studentResponse.setGroupName(group.getGroupName());

                var checkGroup_Students = iAccount_groupRepository.findAccount_GroupsByGroupId(group.getId());
                List<StudentRequest> studentRequests = new ArrayList<>();
                StudentRequest studentRequest = new StudentRequest();

                for (Account_Group account_group : checkGroup_Students) {
                    var checkAccount = iAccountRepository.findById(account_group.getAccountId()).get();
                    studentRequest.setUsername(checkAccount.getUsername());
                    studentRequest.setCode(checkAccount.getCode());
                    studentRequest.setPosition(checkAccount.getPosition());
                    studentRequests.add(studentRequest);
                }
                group_studentResponse.setStudents(studentRequests);
                group_studentResponses.add(group_studentResponse);
            }
            classroom_group_studentResponse.setGroup_studentResponses(group_studentResponses);
            classroom_group_studentResponses.add(classroom_group_studentResponse);
            return ResponseEntity.ok().body(classroom_group_studentResponses);
        }
        return ResponseEntity.badRequest().body(new MessageResponse("classroom not exists"));
    }

    // tu lop hoc ID lay duoc danh sach group ID,
    // tu group ID lay danh sach thong tin hoc sinh.
    // return (groupID, va danh sach thuoc group nay)

    // group 1 co danh sach sinh vien 1
    // group 2 co danh sach sinh vien 2
    // lay danh sach group theo classrooom ID, tra ra ID group
    // duyet danh sach groupID thi ta lay, danh sach sinh vien tuong uong voi group ID.
    // tra ra
    // ten lop
    //  ten nhom
    //      List<Student>
    //

    public ResponseEntity<?> getClassroomToGroupDetailAndStudents(Long classroomId, String groupName) {
        var optional_Classroom = iClassroomRepository.findById(classroomId);
        if (optional_Classroom.isPresent()) {
            Classroom classroom = optional_Classroom.get();

            List<Group_StudentResponse> group_studentResponses = new ArrayList<>();
            Group_StudentResponse group_studentResponse = new Group_StudentResponse();
            var checkClassroom_Groups = iClassroom_groupRepository.findClassroom_GroupsByClassroomId(classroom.getId());
            for (Classroom_Group classroom_group : checkClassroom_Groups) {
                var check_Group = iGroupRepository.findByGroupName(groupName);
                if (check_Group.isPresent()) {
                    Group group = check_Group.get();

                    var checkGroup_Students = iAccount_groupRepository.findAccount_GroupsByGroupId(group.getId());
                    List<StudentRequest> studentRequests = new ArrayList<>();
                    for (Account_Group account_group : checkGroup_Students) {
                        var check_Student = iAccountRepository.findById(account_group.getAccountId()).get();
                        StudentRequest studentRequest = new StudentRequest(check_Student);
                        studentRequests.add(studentRequest);
                    }
                    group_studentResponse.setStudents(studentRequests);
                    group_studentResponses.add(group_studentResponse);
                } else {
                    return ResponseEntity.badRequest().body(new MessageResponse("group not exists"));
                }
            }
            return ResponseEntity.ok().body(group_studentResponse);
        }
        return ResponseEntity.badRequest().body(new MessageResponse("classroom not exists"));
    }


    // check classroomId
    // Nếu tồn tại thì => tìm kiếm danh sách trong classroom ở trong bảng trung gian
    // có classroomId thì sẽ lấy ra được danh sách group => khi tìm được groupId thì sẽ lấy ra được danh sách học sinh
    // rồi lại duyệt danh sách học sinh
    // nếu mà id trùng với id gửi từ request
    // => sẽ được xóa khỏi trong danh sách
    public ResponseEntity<?> removeStudentsToGroup(Long classroomId, String groupName, Long studentId) {
        var check_classroom = iClassroomRepository.findById(classroomId);
        if (check_classroom.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("classroom not exists"));
        }
        Classroom classroom = check_classroom.get();

        List<Group_StudentResponse> group_studentResponses = new ArrayList<>();
        Group_StudentResponse group_studentResponse = new Group_StudentResponse();
        var check_classroom_groups = iClassroom_groupRepository.findClassroom_GroupsByClassroomId(classroom.getId());
        for (Classroom_Group classroom_group : check_classroom_groups) {
            var check_group = iGroupRepository.findById(classroom_group.getGroupId());
            Group group = check_group.get();

            var check_group_request = iGroupRepository.findByGroupName(groupName);
            if (check_group_request.isEmpty()) {
                return ResponseEntity.badRequest().body(new MessageResponse("group " + groupName + "not exists"));
            }
//            if (!group.getGroupName().equals(check_group_request.get().getGroupName())) {
//                return ResponseEntity.badRequest().body(new MessageResponse("group " + groupName + " not exists in the classroom"));
//            }

            var check_group_students = iAccount_groupRepository.findAccount_GroupsByGroupId(group.getId());
            List<StudentRequest> studentRequests = new ArrayList<>();
            for (Account_Group account_group : check_group_students) {
                var check_student = iAccountRepository.findById(account_group.getAccountId());
                if (check_student.isEmpty()) {
                    return ResponseEntity.badRequest().body(new MessageResponse("student not exists"));
                }
                var student_request = iAccount_groupRepository.findAccount_GroupByAccountId(studentId).get();
                if (check_student.get().getId().equals(student_request.getAccountId())) {
                    iAccount_groupRepository.deleteById(student_request.getId());
                }
            }
        }
        return ResponseEntity.ok().body(new MessageResponse("remove student from group"));
    }


}

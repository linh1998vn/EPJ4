package com.example.fai_edunext.services;

import com.example.fai_edunext.dto.request.*;
import com.example.fai_edunext.dto.response.MessageResponse;
import com.example.fai_edunext.dto.response.Teacher_InformationResponse;
import com.example.fai_edunext.dto.response.Teacher_ClassroomsResponse;
import com.example.fai_edunext.entity.Account;
import com.example.fai_edunext.entity.Role;
import com.example.fai_edunext.entity.TeacherInformation;
import com.example.fai_edunext.entity.relationship.Account_Classroom;
import com.example.fai_edunext.entity.relationship.Account_Teacher_Detail;
import com.example.fai_edunext.repository.IAccountRepository;
import com.example.fai_edunext.repository.ITeacherDetailRepository;
import com.example.fai_edunext.repository.IUserRepository;
import com.example.fai_edunext.repository.relationship_Repository.IAccount_ClassroomRepository;
import com.example.fai_edunext.repository.IClassroomRepository;
import com.example.fai_edunext.repository.relationship_Repository.IAccount_Teacher_InformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TeacherService {
    @Autowired
    IAccountRepository iAccountRepository;

    @Autowired
    IClassroomRepository iClassroomRepository;

    @Autowired
    ITeacherDetailRepository iTeacherDetailRepository;

    @Autowired
    IAccount_ClassroomRepository iAccount_classroomRepository;

    @Autowired
    IAccount_Teacher_InformationRepository iAccount_teacher_informationRepository;

    @Autowired
    IUserRepository iUserRepository;

    public ResponseEntity<?> findAllTeacher(){
        var find_all_teachers = iAccountRepository.findAllTeacher("teacher");
        if (find_all_teachers.size() == 0){
            return ResponseEntity.badRequest().body(new MessageResponse("teacher not record"));
        }
        List<Teacher_InformationResponse> teacher_informationResponses = new ArrayList<>();
        Teacher_InformationResponse teacher_informationResponse = new Teacher_InformationResponse();
        for (Account account : find_all_teachers){
            var check_account = iAccountRepository.findById(account.getId()).get();
            teacher_informationResponse.setUsername(check_account.getUsername());
            teacher_informationResponse.setCode(check_account.getCode());

            // tìm thông tin teacher
            var find_teacher_information = iAccount_teacher_informationRepository.findAccount_Teacher_DetailByAccountId(check_account.getId()).get();
            var check_teacher_information = iTeacherDetailRepository.findById(find_teacher_information.getTeacherDetail()).get();
            TeacherDetailInformationRequest teacherDetailInformationRequest = new TeacherDetailInformationRequest(check_teacher_information);
            teacher_informationResponse.setInformation(teacherDetailInformationRequest);
            teacher_informationResponses.add(teacher_informationResponse);
        }
        return ResponseEntity.ok().body(teacher_informationResponses);
    }


    public ResponseEntity<?> showTeacherForClassrooms() {
        List<Account> accounts = iAccountRepository.findAllTeacher("teacher");
        if (accounts.size() == 0) {
            return ResponseEntity.badRequest().body(new MessageResponse("Account not record"));
        } else {
            List<Teacher_ClassroomsResponse> teacher_classroomsResponses = new ArrayList<>();
            for (Account account : accounts) {
                Teacher_ClassroomsResponse teacher_classroomsResponse = new Teacher_ClassroomsResponse();
                teacher_classroomsResponse.setUsername(account.getUsername());
                teacher_classroomsResponse.setCode(account.getCode());

                List<Account_Classroom> account_classrooms = iAccount_classroomRepository.findAccount_ClassroomsByAccountId(account.getId());
                List<ClassroomRequest> classroomRequests = new ArrayList<>();
                for (Account_Classroom account_classroom : account_classrooms) {
                    var checkClassroom = iClassroomRepository.findById(account_classroom.getClassroomId()).get();
                    ClassroomRequest classroomRequest = new ClassroomRequest(checkClassroom);
                    classroomRequests.add(classroomRequest);
                }
                teacher_classroomsResponse.setClassrooms(classroomRequests);
                teacher_classroomsResponses.add(teacher_classroomsResponse);
            }
            return ResponseEntity.ok().body(teacher_classroomsResponses);
        }
    }

    public ResponseEntity<?> getTeacherToClassroomByTeacherId(Long id) {
        var checkAccount = iAccountRepository.findById(id).get();
        List<String> roleNames = checkAccount.getRoles().stream().map(Role::getName).collect(Collectors.toList());
        if (roleNames.contains("teacher")) {
//            var findAccountTeacher = iAccount_classroomRepository.findAccount_ClassroomByAccountId(checkAccount.getId()).get();
            Teacher_ClassroomsResponse teacher_classroomsResponse = new Teacher_ClassroomsResponse();
//            AccountTeacherRequest accountTeacherRequest = new AccountTeacherRequest(checkAccount);
            teacher_classroomsResponse.setUsername(checkAccount.getUsername());
            teacher_classroomsResponse.setCode(checkAccount.getCode());
            List<Account_Classroom> account_classrooms = iAccount_classroomRepository.findAccount_ClassroomsByAccountId(checkAccount.getId());
            List<ClassroomRequest> classroomRequests = new ArrayList<>();
            for (Account_Classroom account_classroom : account_classrooms) {
                var findClassroom = iClassroomRepository.findById(account_classroom.getClassroomId()).get();
                ClassroomRequest classroomRequest = new ClassroomRequest(findClassroom);
                classroomRequests.add(classroomRequest);
            }
            teacher_classroomsResponse.setClassrooms(classroomRequests);
            return ResponseEntity.ok().body(teacher_classroomsResponse);
        } else {
            return ResponseEntity.badRequest().body(new MessageResponse("this is not a teacher account"));
        }
    }

    // thêm 1 giáo viên có thể dạy được nhiều lớp đã check được trùng đối tượng

    public ResponseEntity<?> addTeacherToClassrooms(AddTeacher_ClassroomsRequest addTeacher_classroomsRequest) {
        var checkAccountTeacher = iAccountRepository.findById(addTeacher_classroomsRequest.getTeacherId()).get();

        if (Objects.isNull(checkAccountTeacher)) {
            return ResponseEntity.badRequest().body(new MessageResponse("teacher not exists"));
        }
        List<ClassroomRequest> classroomRequests = addTeacher_classroomsRequest.getClassrooms();
        for (ClassroomRequest classroomRequest : classroomRequests) {
            Account_Classroom account_classroom = new Account_Classroom();
            account_classroom.setAccountId(checkAccountTeacher.getId());

            var checkClassroom = iClassroomRepository.findById(classroomRequest.getClassroomId()).get();

            if (Objects.isNull(checkClassroom)) {
                return ResponseEntity.badRequest().body(new MessageResponse("classroom not exists"));
            }
            List<Account_Classroom> account_classrooms = iAccount_classroomRepository.findAll();
            List<String> roleNames = checkAccountTeacher.getRoles().stream().map(Role::getName).collect(Collectors.toList());
            if (roleNames.contains("teacher")) {
                if (account_classrooms.size() == 0) {
                    account_classroom.setClassroomId(checkClassroom.getId());
                    iAccount_classroomRepository.save(account_classroom);
                } else {
                    for (Account_Classroom ac : account_classrooms) {
                        if (ac.getAccountId().equals(checkAccountTeacher.getId()) && ac.getClassroomId().equals(checkClassroom.getId())) {
                            return ResponseEntity.badRequest().body(new MessageResponse("already exists"));
                        } else {
                            account_classroom.setClassroomId(checkClassroom.getId());
                            iAccount_classroomRepository.save(account_classroom);
                        }
                    }
                }
            } else {
                return ResponseEntity.badRequest().body(new MessageResponse("This account does not belong to the teacher"));
            }
        }
        return ResponseEntity.ok().body(new MessageResponse("add account teacher to classroom successfully"));
    }

    public ResponseEntity<?> addTeacherInformation(Long id, TeacherDetailInformationRequest teacherDetailInformationRequest) {
        Account_Teacher_Detail account_teacher_detail = new Account_Teacher_Detail();
        var find_account = iAccountRepository.findById(id);
        if (find_account.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("teacher not exists"));
        }
        Account account = find_account.get();
        if (!account.getPosition().equalsIgnoreCase("teacher")) {
            return ResponseEntity.badRequest().body(new MessageResponse("teacher not exists"));
        }
        account_teacher_detail.setAccountId(account.getId());
        if (iAccount_teacher_informationRepository.existsAccount_Teacher_DetailByAccountId(account.getId())) {
            return ResponseEntity.badRequest().body(new MessageResponse("this account already exists"));
        }

        TeacherInformation teacherInformation = new TeacherInformation();
        teacherInformation.setFirstName(teacherDetailInformationRequest.getFirstName());
        teacherInformation.setLastName(teacherDetailInformationRequest.getLastName());
        teacherInformation.setPhone(teacherDetailInformationRequest.getPhone());
        if (iTeacherDetailRepository.existsByPhone(teacherInformation.getPhone())) {
            return ResponseEntity.badRequest().body(new MessageResponse("phone already exists"));
        }
        teacherInformation.setBirthday(teacherDetailInformationRequest.getBirthday());
        teacherInformation.setAddress(teacherDetailInformationRequest.getAddress());
        teacherInformation.setDegree(teacherDetailInformationRequest.getDegree());
        iTeacherDetailRepository.save(teacherInformation);

        account_teacher_detail.setTeacherDetail(teacherInformation.getId());

        iAccount_teacher_informationRepository.save(account_teacher_detail);

        return ResponseEntity.ok().body(new MessageResponse("Add information teacher successfully"));
    }

    public ResponseEntity<?> updateTeacherInformation(Long id, TeacherDetailInformationRequest teacherDetailInformationRequest) {
        var teacherInformation = iTeacherDetailRepository.findById(id).get();
        if (Objects.isNull(teacherInformation)) {
            return ResponseEntity.badRequest().body(new MessageResponse("teacher information not exists"));
        }
        teacherInformation.setFirstName(teacherDetailInformationRequest.getFirstName());
        teacherInformation.setLastName(teacherDetailInformationRequest.getLastName());
        teacherInformation.setPhone(teacherDetailInformationRequest.getPhone());
        if (iTeacherDetailRepository.existsByPhone(teacherInformation.getPhone())) {
            return ResponseEntity.badRequest().body(new MessageResponse("phone already exists"));
        }
        if (teacherInformation.getPhone() == null) {
            teacherInformation.setPhone(teacherInformation.getPhone());
        } else {
            teacherInformation.setPhone(teacherDetailInformationRequest.getPhone());
        }
        teacherInformation.setBirthday(teacherDetailInformationRequest.getBirthday());
        teacherInformation.setAddress(teacherDetailInformationRequest.getAddress());
        teacherInformation.setDegree(teacherDetailInformationRequest.getDegree());
        iTeacherDetailRepository.save(teacherInformation);
        return ResponseEntity.ok().body(new MessageResponse("update teacher information successfully"));
    }

    public ResponseEntity<?> getTeacherDetailById(Long id) {
        Teacher_InformationResponse teacherInformationResponse = new Teacher_InformationResponse();
        var find_account_teacher_detail = iAccount_teacher_informationRepository.findAccount_Teacher_DetailByAccountId(id);
        if (find_account_teacher_detail.isEmpty()){
            return ResponseEntity.badRequest().body(new MessageResponse("teacher not exists"));
        }

        Account_Teacher_Detail account_teacher_detail = find_account_teacher_detail.get();
        var accountTeacher = iAccountRepository.findById(account_teacher_detail.getAccountId()).get();
        teacherInformationResponse.setUsername(accountTeacher.getUsername());
        teacherInformationResponse.setCode(accountTeacher.getCode());

        var teacherDetail = iTeacherDetailRepository.findById(account_teacher_detail.getTeacherDetail()).get();
        TeacherDetailInformationRequest teacherDetailInformationRequest = new TeacherDetailInformationRequest(teacherDetail);

        teacherInformationResponse.setInformation(teacherDetailInformationRequest);

        return ResponseEntity.ok().body(teacherInformationResponse);

    }
}

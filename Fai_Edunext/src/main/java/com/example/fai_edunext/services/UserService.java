package com.example.fai_edunext.services;

import com.example.fai_edunext.dto.request.UserRequest;
import com.example.fai_edunext.dto.request.User_CourseRequest;
import com.example.fai_edunext.dto.response.MessageResponse;
import com.example.fai_edunext.entity.Classroom;
import com.example.fai_edunext.entity.Course;
import com.example.fai_edunext.entity.User;
import com.example.fai_edunext.entity.relationship.User_Course;
import com.example.fai_edunext.repository.ICourseRepository;
import com.example.fai_edunext.repository.IUserRepository;
import com.example.fai_edunext.repository.relationship_Repository.IUser_CourseRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserService {
    @Autowired
    IUserRepository iUserRepository;

    @Autowired
    ICourseRepository iCourseRepository;

    @Autowired
    IUser_CourseRepository iUser_courseRepository;

    public ResponseEntity<?> getAllUserWaitingApprove(){
        List<User> usersWaitingApprove = iUserRepository.findAllStudentWaitingApprove();
        if (usersWaitingApprove.size() == 0){
            return ResponseEntity.badRequest().body(new MessageResponse("student not record"));
        }
        return ResponseEntity.ok().body(usersWaitingApprove);
    }

    /*
        * user sẽ gửi thông tin cá nhân và đăng ký khóa học
        - user gửi request và lưu vào db
        - khi đăng ký khóa học thì phải duyệt danh sách khóa học đang có
    */
    public ResponseEntity<?> createUserRequest(User_CourseRequest user_courseRequest){
        User user = new User();
        user.setFirstName(user_courseRequest.getFirstName());
        user.setLastName(user_courseRequest.getLastName());
        user.setBirthday(user_courseRequest.getBirthday());
        user.setPhone(user_courseRequest.getPhone());
        if (iUserRepository.existsByPhone(user.getPhone())){
            return ResponseEntity.badRequest().body(new MessageResponse("phone already exists"));
        }
        user.setAddress(user_courseRequest.getAddress());
        user.setEnable(false);
        iUserRepository.save(user);
        User_Course user_course = new User_Course();
        user_course.setUserId(user.getId());
        var check_courses = iCourseRepository.findAll();
        for (Course course : check_courses){
            var find_course = iCourseRepository.findById(course.getId()).get();
            if (find_course.getId().equals(user_courseRequest.getCourseId())){
                user_course.setCourseId(user_courseRequest.getCourseId());
                user_course.setStatus("waiting");
                iUser_courseRepository.save(user_course);
            }
        }
//        user_course
        return ResponseEntity.ok().body(new MessageResponse("add user information successfully"));
    }

    public ResponseEntity<?> searchUser(String query){
        List<User> users = iUserRepository.searchUsers(query);
        return ResponseEntity.ok().body(users);
    }
}

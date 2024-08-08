package com.example.fai_edunext.services;

import com.example.fai_edunext.dto.request.LoginRequest;
import com.example.fai_edunext.dto.request.RegisterRequest;
import com.example.fai_edunext.dto.response.JwtResponse;
import com.example.fai_edunext.dto.response.MessageResponse;
import com.example.fai_edunext.entity.Account;
import com.example.fai_edunext.entity.Role;
import com.example.fai_edunext.entity.User;
import com.example.fai_edunext.entity.relationship.Account_Course;
import com.example.fai_edunext.entity.relationship.Account_Subject;
import com.example.fai_edunext.entity.relationship.Account_User;
import com.example.fai_edunext.entity.relationship.Course_Subject;
import com.example.fai_edunext.repository.IAccountRepository;
import com.example.fai_edunext.repository.relationship_Repository.*;
import com.example.fai_edunext.repository.IRoleRepository;
import com.example.fai_edunext.repository.IUserRepository;
import com.example.fai_edunext.security.jwt.JwtUtils;
import com.example.fai_edunext.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    IAccountRepository iAccountRepository;

    @Autowired
    IUser_CourseRepository iUser_courseRepository;

    @Autowired
    IAccount_CourseRepository iAccount_courseRepository;

    @Autowired
    IRoleRepository iRoleRepository;

    @Autowired
    IAccount_UserRepository iAccount_userRepository;

    @Autowired
    IUserRepository iUserRepository;

    @Autowired
    ICourse_SubjectRepository iCourse_subjectRepository;

    @Autowired
    IAccount_SubjectRepository iAccount_subjectRepository;

    @Autowired
    JwtUtils jwtUtils;

    public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken obj = new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        );
        Authentication authentication = authenticationManager.authenticate(obj);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(
                jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getCode(),
                roles
        ));
    }

    public ResponseEntity<?> registerUser(Long id, RegisterRequest registerRequest) {
        var find_user = iUserRepository.findById(id);
        if (find_user.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("user info not exists"));
        }

        User user = find_user.get();

        // kiểm tra Email có tồn tại trong User nhập User từ registerDTO
        if (iAccountRepository.existsByUsername(registerRequest.getUsername())) {
            // Trả về Lỗi khi Tồn tại Email
            return ResponseEntity.badRequest().body(new MessageResponse("Error: User email is already taken"));
        }
        if (iAccount_userRepository.existsAccount_UserByUserId(user.getId())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: user information already exists in another account"));
        }
        // Tạo 1 Đối tượng User
        Account account = new Account();
        // nhận các dữ liệu của thuộc tính từ registerDTO vào user
        account.setCode("ntth" + "210200" + user.getId());
        account.setUsername(user.getFirstName() + account.getCode());
        account.setPassword(encoder.encode(registerRequest.getPassword()));
        // Tạo 1 danh sách chuỗi
        Set<String> strRoles = registerRequest.getRoles(); // đang bằng null
        // Dùng hàm set để không cho nhập trùng role
        Set<Role> roles = new HashSet<>();

        // Nếu chuỗi strRoles == null
        if (strRoles == null) {
            Role studentRole = iRoleRepository.findByName("student")
                    .orElseThrow(() -> new RuntimeException("Error: Role is not Found"));
            roles.add(studentRole);
        } else { // Nếu chuỗi strRoles khác null thì sẽ check các điều kiện
            strRoles.forEach(role -> {
                Role studentRole = iRoleRepository.findByName("student")
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(studentRole);
            });
        }

        account.setRoles(roles);

        account.setPosition(account.getRoles().stream().findFirst().get().getName());
        iAccountRepository.save(account);

        user.setEnable(true);
        iUserRepository.save(user);

        Account_User account_user = new Account_User();
        account_user.setUserId(user.getId());
        account_user.setAccountId(account.getId());
        iAccount_userRepository.save(account_user);

        var find_user_course = iUser_courseRepository.findUser_CourseByUserId(id).get();
        if (find_user_course.getId() == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Please select the course you want to apply!"));
        }
        Account_Course account_course = new Account_Course();
        account_course.setAccountId(account.getId());
        account_course.setCourseId(find_user_course.getCourseId());
        account_course.setStatus("approved");
        iAccount_courseRepository.save(account_course);

        // tìm ra khóa học mà đã được đăng ký
        var find_course_subjects = iCourse_subjectRepository.findCourse_SubjectsByCourseId(find_user_course.getCourseId());
        for (Course_Subject course_subject : find_course_subjects) {
            Account_Subject account_subject = new Account_Subject();
            account_subject.setAccountId(account.getId());
            account_subject.setSubjectId(course_subject.getSubjectId());
            iAccount_subjectRepository.save(account_subject);
        }
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));

    }

    public ResponseEntity<?> registerTeacherOrAdmin(RegisterRequest registerRequest) {
        // kiểm tra Email có tồn tại trong User nhập User từ registerDTO
        if (iAccountRepository.existsByUsername(registerRequest.getUsername())) {
            // Trả về Lỗi khi Tồn tại Email
            return ResponseEntity.badRequest().body(new MessageResponse("Error: User email is already taken"));
        }
        // Tạo 1 Đối tượng User
        Account account = new Account();
        // nhận các dữ liệu của thuộc tính từ registerDTO vào user

        account.setUsername(registerRequest.getUsername());
        account.setPassword(encoder.encode(registerRequest.getPassword()));
        // Tạo 1 danh sách chuỗi
        Set<String> strRoles = registerRequest.getRoles(); // đang bằng null
        // Dùng hàm set để không cho nhập trùng role
        Set<Role> roles = new HashSet<>();

        // Nếu chuỗi strRoles == null
        if (strRoles == null) {
            Role studentRole = iRoleRepository.findByName("teacher")
                    .orElseThrow(() -> new RuntimeException("Error: Role is not Found"));
            roles.add(studentRole);
        } else { // Nếu chuỗi strRoles khác null thì sẽ check các điều kiện
            strRoles.forEach(role -> { // duyệt từng thành phần trong danh sách chuỗi strRoles
                switch (role) {
                    case "admin": // Nếu chọn điều kiện admin
                        // thì nó sẽ kiểm tra chưa tồn tại trong Role hay chưa
                        Role adminRole = iRoleRepository.findByName("admin")
                                // tồn tại thì sẽ hiển thị lỗi
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        // Nếu chưa tồn tại thì nó sẽ thêm vào danh sách
                        roles.add(adminRole);
                        break;
                    default:
                        Role teacherRole = iRoleRepository.findByName("teacher")
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(teacherRole);
                        break;
                }
            });
        }
        // nhận dữ liệu 1 list từ roles
        account.setRoles(roles);
        account.setPosition(account.getRoles().stream().findFirst().get().getName());
        // lưu dữ liệu của user
        account = iAccountRepository.save(account);

        account.setCode("lt" + "210200" + account.getId());
        iAccountRepository.save(account);
        return ResponseEntity.ok(new MessageResponse("teacher registered successfully!"));
    }
}

package com.example.fai_edunext.controller;

import com.example.fai_edunext.dto.request.UserRequest;
import com.example.fai_edunext.dto.request.User_CourseRequest;
import com.example.fai_edunext.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    // @PreAuthorize("hasAuthority('admin')")
    @GetMapping("get-all-user-waiting-approve")
    public ResponseEntity<?> getAllUserWaitingApprove(){
        return userService.getAllUserWaitingApprove();
    }

    @PostMapping("/send-request")
    public ResponseEntity<?> userSendRequest (@RequestBody User_CourseRequest user_courseRequest) {
        return userService.createUserRequest(user_courseRequest);
    }

    // @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/search")
    public ResponseEntity<?> searchClassroom(@RequestParam(name = "query") String query){
        return userService.searchUser(query);
    }
}

package com.example.fai_edunext.controller;

import com.example.fai_edunext.dto.request.LoginRequest;
import com.example.fai_edunext.dto.request.RegisterRequest;
import com.example.fai_edunext.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/sign-in")
    public ResponseEntity<?> authenticateUse(@RequestBody LoginRequest loginRequest){
        return authService.authenticateUser(loginRequest);
    }

    // User Approve

    // @PreAuthorize("hasAuthority('admin')")
    @PostMapping("/sign-up")
    public ResponseEntity<?> registerUser(@Validated @RequestParam(name = "userId") Long id, @RequestBody RegisterRequest registerRequest){
        return authService.registerUser(id, registerRequest);
    }

    // teacher or admin
    // @PreAuthorize("hasAuthority('admin')")
    @PostMapping("/sign-up-teacher")
    public ResponseEntity<?> registerTeacherOrAdmin(@Validated @RequestBody RegisterRequest registerRequest){
        return authService.registerTeacherOrAdmin(registerRequest);
    }
}

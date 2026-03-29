package com.taskmanagement.backend.controller;

import com.taskmanagement.backend.dto.auth.AuthResponse;
import com.taskmanagement.backend.dto.auth.LoginRequest;
import com.taskmanagement.backend.dto.auth.RegisterRequest;
import com.taskmanagement.backend.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final AuthService authService;

    public static final String REGISTER_URL = "/api/v1/auth/register";
    public static final String LOGIN_URL = "/api/v1/auth/login";

    @PostMapping(REGISTER_URL)
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(request));
    }

    @PostMapping(LOGIN_URL)
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
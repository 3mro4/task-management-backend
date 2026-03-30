package com.taskmanagement.backend.controller;

import com.taskmanagement.backend.dto.auth.AuthResponse;
import com.taskmanagement.backend.dto.auth.LoginRequest;
import com.taskmanagement.backend.dto.auth.RegisterRequest;
import com.taskmanagement.backend.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {

    public static final String REGISTER_URL = "/api/v1/auth/register";
    public static final String LOGIN_URL = "/api/v1/auth/login";

    private final AuthService authService;

    @PostMapping(REGISTER_URL)
    @ResponseStatus(HttpStatus.CREATED)
    public AuthResponse register(@Valid @RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping(LOGIN_URL)
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
package com.taskmanagement.backend.service;

import com.taskmanagement.backend.dto.auth.AuthResponse;
import com.taskmanagement.backend.dto.auth.LoginRequest;
import com.taskmanagement.backend.dto.auth.RegisterRequest;

public interface AuthService {


    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}

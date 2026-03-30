package com.taskmanagement.backend.service;

import com.taskmanagement.backend.dto.PageResponse;
import com.taskmanagement.backend.dto.auth.RegisterRequest;
import com.taskmanagement.backend.dto.user.UpdateUserRequest;
import com.taskmanagement.backend.dto.user.UserDto;
import org.springframework.data.domain.Pageable;


import java.util.UUID;


 public interface UserService {


     PageResponse<UserDto> getAllUsers(Pageable pageable);
     UserDto getUserById(UUID id);
     UserDto createUser(RegisterRequest request);
     UserDto updateUser(UUID id, UpdateUserRequest request);
     void deleteUser(UUID id);

 }

package com.taskmanagement.backend.service;

import com.taskmanagement.backend.dto.auth.RegisterRequest;
import com.taskmanagement.backend.dto.user.UpdateUserRequest;
import com.taskmanagement.backend.dto.user.UserDto;

import java.util.List;
import java.util.UUID;


 public interface UserService {


     List<UserDto> getAllUsers();
     UserDto getUserById(UUID id);
     UserDto createUser(RegisterRequest request);
     UserDto updateUser(UUID id, UpdateUserRequest request);
     void deleteUser(UUID id);

 }

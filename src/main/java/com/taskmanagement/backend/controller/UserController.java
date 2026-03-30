package com.taskmanagement.backend.controller;

import com.taskmanagement.backend.dto.PageResponse;
import com.taskmanagement.backend.dto.auth.RegisterRequest;
import com.taskmanagement.backend.dto.user.UpdateUserRequest;
import com.taskmanagement.backend.dto.user.UserDto;
import com.taskmanagement.backend.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    public static final String BASE_URL = "/api/v1/users";
    public static final String BASE_URL_WITH_ID = BASE_URL + "/{id}";

    // Get all users with pagination ------------------------------------------------------
    @GetMapping(BASE_URL)
    public PageResponse<UserDto> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userService.getAllUsers(pageable);
    }

    // Get user by ID ------------------------------------------------------
    @GetMapping(BASE_URL_WITH_ID)
    public UserDto getUserById(@PathVariable UUID id) {
        return userService.getUserById(id);
    }

    // Create a new user ------------------------------------------------------
    @PostMapping(BASE_URL)
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@Valid @RequestBody RegisterRequest request) {
        return userService.createUser(request);
    }

    // Update an existing user -------------------------------------------------
    @PutMapping(BASE_URL_WITH_ID)
    public UserDto updateUser(@PathVariable UUID id,
                              @Valid @RequestBody UpdateUserRequest request) {
        return userService.updateUser(id, request);
    }

    // Delete a user by ID ------------------------------------------------------
    @DeleteMapping(BASE_URL_WITH_ID)
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
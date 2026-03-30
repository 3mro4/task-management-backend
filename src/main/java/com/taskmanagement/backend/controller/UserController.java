package com.taskmanagement.backend.controller;

import com.taskmanagement.backend.dto.auth.RegisterRequest;
import com.taskmanagement.backend.dto.user.UpdateUserRequest;
import com.taskmanagement.backend.dto.user.UserDto;
import com.taskmanagement.backend.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UserController {

    public static final String BASE_URL = "/api/v1/users";
    public static final String BASE_URL_WITH_ID = BASE_URL + "/{id}";

    private final UserService userService;

    @GetMapping(BASE_URL)
    public Page<UserDto> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return userService.getAllUsers(page, size);
    }

    @GetMapping(BASE_URL_WITH_ID)
    public UserDto getUserById(@PathVariable UUID id) {
        return userService.getUserById(id);
    }

    @PostMapping(BASE_URL)
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@Valid @RequestBody RegisterRequest request) {
        return userService.createUser(request);
    }

    @PutMapping(BASE_URL_WITH_ID)
    public UserDto updateUser(@PathVariable UUID id,
                              @Valid @RequestBody UpdateUserRequest request) {
        return userService.updateUser(id, request);
    }

    @DeleteMapping(BASE_URL_WITH_ID)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
    }
}
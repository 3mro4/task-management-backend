package com.taskmanagement.backend.controller;

import com.taskmanagement.backend.dto.PageResponse;
import com.taskmanagement.backend.dto.task.CreateTaskRequest;
import com.taskmanagement.backend.dto.task.TaskDto;
import com.taskmanagement.backend.dto.task.UpdateTaskRequest;
import com.taskmanagement.backend.service.TaskService;
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
public class TaskController {

//use @ResponseStatus(HttpStatus.CREATED), user cross origin config -- DONE
// add pagination to get all -- DONE

    public static final String BASE_URL = "/api/v1/tasks";
    public static final String BASE_URL_WITH_ID = BASE_URL + "/{id}";

    private final TaskService taskService;

    // Get all tasks with pagination ------------------------------------------------------
    @GetMapping(BASE_URL)
    public PageResponse<TaskDto> getAllTasks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return taskService.getAll(pageable);
    }

    // Get task by id ----------------------------------------------------------------------
    @GetMapping(BASE_URL_WITH_ID)
    public TaskDto getTaskById(@PathVariable UUID id) {
        return taskService.getById(id);
    }

    // Create new task ----------------------------------------------------------------------
    @PostMapping(BASE_URL)
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDto createTask(@Valid @RequestBody CreateTaskRequest request) {
        return taskService.create(request);
    }

    // Update existing task ----------------------------------------------------------------------
    @PutMapping(BASE_URL_WITH_ID)
    public TaskDto updateTask(@PathVariable UUID id,
                                              @Valid @RequestBody UpdateTaskRequest request) {
        return taskService.update(id, request);
    }

    // Delete task by id ----------------------------------------------------------------------
    @DeleteMapping(BASE_URL_WITH_ID)
    public ResponseEntity<Void> deleteTask(@PathVariable UUID id) {
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
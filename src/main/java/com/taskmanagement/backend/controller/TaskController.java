package com.taskmanagement.backend.controller;

import com.taskmanagement.backend.dto.task.CreateTaskRequest;
import com.taskmanagement.backend.dto.task.TaskDto;
import com.taskmanagement.backend.dto.task.UpdateTaskRequest;
import com.taskmanagement.backend.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class TaskController {

//use @ResponseStatus(HttpStatus.CREATED), user cross origin config
//add pagination to all
    public static final String BASE_URL = "/api/v1/tasks";
    public static final String BASE_URL_WITH_ID = BASE_URL + "/{id}";

    private final TaskService taskService;

    @GetMapping(BASE_URL)
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAll());
    }

    @GetMapping(BASE_URL_WITH_ID)
    public ResponseEntity<TaskDto> getTaskById(@PathVariable UUID id) {
        return ResponseEntity.ok(taskService.getById(id));
    }
    @PostMapping(BASE_URL)
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDto createTask(@Valid @RequestBody CreateTaskRequest request) {
        return taskService.create(request);
    }

    @PutMapping(BASE_URL_WITH_ID)
    public ResponseEntity<TaskDto> updateTask(@PathVariable UUID id,
                                              @Valid @RequestBody UpdateTaskRequest request) {
        return ResponseEntity.ok(taskService.update(id, request));
    }

    @DeleteMapping(BASE_URL_WITH_ID)
    public ResponseEntity<Void> deleteTask(@PathVariable UUID id) {
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
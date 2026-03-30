package com.taskmanagement.backend.controller;

import com.taskmanagement.backend.dto.task.CreateTaskRequest;
import com.taskmanagement.backend.dto.task.TaskDto;
import com.taskmanagement.backend.dto.task.UpdateTaskRequest;
import com.taskmanagement.backend.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class TaskController {

    public static final String BASE_URL = "/api/v1/tasks";
    public static final String BASE_URL_WITH_ID = BASE_URL + "/{id}";

    private final TaskService taskService;

    @GetMapping(BASE_URL)
    public Page<TaskDto> getAllTasks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return taskService.getAll(page, size);
    }

    @GetMapping(BASE_URL_WITH_ID)
    public TaskDto getTaskById(@PathVariable UUID id) {
        return taskService.getById(id);
    }

    @PostMapping(BASE_URL)
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDto createTask(@Valid @RequestBody CreateTaskRequest request) {
        return taskService.create(request);
    }

    @PutMapping(BASE_URL_WITH_ID)
    public TaskDto updateTask(@PathVariable UUID id,
                              @Valid @RequestBody UpdateTaskRequest request) {
        return taskService.update(id, request);
    }

    @DeleteMapping(BASE_URL_WITH_ID)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable UUID id) {
        taskService.delete(id);
    }
}
package com.taskmanagement.backend.service;

import com.taskmanagement.backend.dto.PageResponse;
import com.taskmanagement.backend.dto.task.CreateTaskRequest;
import com.taskmanagement.backend.dto.task.TaskDto;
import com.taskmanagement.backend.dto.task.UpdateTaskRequest;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface TaskService {

    PageResponse<TaskDto> getAll(Pageable pageable);

    TaskDto getById(UUID id);

    TaskDto create(CreateTaskRequest request);

    TaskDto update(UUID id, UpdateTaskRequest request);

    void delete(UUID id);
}
package com.taskmanagement.backend.service;

import com.taskmanagement.backend.dto.CreateTaskRequest;
import com.taskmanagement.backend.dto.TaskDto;
import com.taskmanagement.backend.dto.UpdateTaskRequest;

import java.util.List;
import java.util.UUID;

public interface TaskService {

    List<TaskDto> getAll();

    TaskDto getById(UUID id);

    TaskDto create(CreateTaskRequest request);

    TaskDto update(UUID id, UpdateTaskRequest request);

    void delete(UUID id);
}
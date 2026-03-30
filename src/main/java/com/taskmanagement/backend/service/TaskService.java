package com.taskmanagement.backend.service;

import com.taskmanagement.backend.dto.task.CreateTaskRequest;
import com.taskmanagement.backend.dto.task.TaskDto;
import com.taskmanagement.backend.dto.task.UpdateTaskRequest;

import java.util.List;
import java.util.UUID;

public interface TaskService {

    List<TaskDto> getAll();

    TaskDto getById(UUID id);

    TaskDto create(CreateTaskRequest request);

    TaskDto update(UUID id, UpdateTaskRequest request);

    void delete(UUID id);
}
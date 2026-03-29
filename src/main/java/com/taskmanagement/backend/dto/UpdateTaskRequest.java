package com.taskmanagement.backend.dto;

import com.taskmanagement.backend.entity.Priority;
import com.taskmanagement.backend.entity.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class UpdateTaskRequest {

    //should be removed from the request body and taken from the path variable in the controller
    @NotBlank(message = "Task title is required")
    private String title;

    @NotBlank(message = "Task description is required")
    private String description;

    @NotNull(message = "Task priority is required")
    private Priority priority;

    @NotNull(message = "Task status is required")
    private Status status;

    @NotNull(message = "Task due date is required")
    private LocalDate dueDate;

    //should be removed from the request body and taken from the path variable in the controller
    @NotNull(message = "Project id is required")
    private UUID projectId;

    @NotNull(message = "Assignee id is required")
    private UUID assigneeId;
}
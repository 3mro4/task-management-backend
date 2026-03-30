package com.taskmanagement.backend.dto.task;

import com.taskmanagement.backend.enums.Priority;
import com.taskmanagement.backend.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class UpdateTaskRequest {



    @NotBlank(message = "Task description is required")
    private String description;

    @NotNull(message = "Task priority is required")
    private Priority priority;

    @NotNull(message = "Task status is required")
    private Status status;

    @NotNull(message = "Task due date is required")
    private LocalDate dueDate;



    @NotNull(message = "Assignee id is required")
    private UUID assigneeId;
}
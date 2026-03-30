package com.taskmanagement.backend.dto.task;

import com.taskmanagement.backend.enums.Priority;
import com.taskmanagement.backend.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {
    private UUID id;
    private String title;
    private String description;
    private Priority priority;
    private Status status;
    private LocalDate dueDate;
    private UUID projectId;
    private String projectName;
    private UUID assigneeId;
    private String assigneeName;
    private LocalDateTime createdAt;
}
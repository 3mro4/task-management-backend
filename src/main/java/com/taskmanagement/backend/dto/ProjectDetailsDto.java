package com.taskmanagement.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDetailsDto {
    private UUID id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private List<String> currentMembers;
    private Map<String, Long> tasksByPriority;
}
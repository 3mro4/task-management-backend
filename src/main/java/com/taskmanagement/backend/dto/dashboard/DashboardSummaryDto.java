package com.taskmanagement.backend.dto.dashboard;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashboardSummaryDto {
    private long totalTasks;
    private Map<String, Long> tasksByStatus;
    private Map<String, Long> tasksByPriority;
    private Map<String, Long> tasksByProject;
    private Map<String, Long> tasksByUser;
}
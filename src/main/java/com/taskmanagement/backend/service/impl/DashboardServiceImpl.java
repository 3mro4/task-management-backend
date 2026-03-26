package com.taskmanagement.backend.service.impl;

import com.taskmanagement.backend.entity.Priority;
import com.taskmanagement.backend.entity.Status;
import com.taskmanagement.backend.repository.TaskRepository;
import com.taskmanagement.backend.repository.UserRepository;
import com.taskmanagement.backend.repository.ProjectRepository;
import com.taskmanagement.backend.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    public Map<String, Object> getSummary() {
        Map<String, Object> summary = new HashMap<>();

        // Total tasks
        summary.put("totalTasks", taskRepository.count());

        // Tasks by status
        Map<String, Long> byStatus = new HashMap<>();
        byStatus.put("TODO", taskRepository.countByStatus(Status.TODO));
        byStatus.put("IN_PROGRESS", taskRepository.countByStatus(Status.IN_PROGRESS));
        byStatus.put("DONE", taskRepository.countByStatus(Status.DONE));
        summary.put("tasksByStatus", byStatus);

        // Tasks by priority
        Map<String, Long> byPriority = new HashMap<>();
        byPriority.put("LOW", taskRepository.countByPriority(Priority.LOW));
        byPriority.put("MEDIUM", taskRepository.countByPriority(Priority.MEDIUM));
        byPriority.put("HIGH", taskRepository.countByPriority(Priority.HIGH));
        summary.put("tasksByPriority", byPriority);

        // Tasks by project
        Map<String, Long> byProject = new HashMap<>();
        projectRepository.findAll().forEach(project -> {
            long count = taskRepository.findByProjectId(project.getId()).size();
            byProject.put(project.getName(), count);
        });
        summary.put("tasksByProject", byProject);

        // Tasks by user
        Map<String, Long> byUser = new HashMap<>();
        userRepository.findAll().forEach(user -> {
            long count = taskRepository.findByAssigneeId(user.getId()).size();
            byUser.put(user.getFirstName() + " " + user.getLastName(), count);
        });
        summary.put("tasksByUser", byUser);

        return summary;
    }
}
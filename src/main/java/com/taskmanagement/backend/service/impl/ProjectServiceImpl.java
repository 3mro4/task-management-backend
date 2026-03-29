package com.taskmanagement.backend.service.impl;

import com.taskmanagement.backend.dto.CreateProjectRequest;
import com.taskmanagement.backend.dto.ProjectDetailsDto;
import com.taskmanagement.backend.dto.ProjectDto;
import com.taskmanagement.backend.entity.Project;
import com.taskmanagement.backend.entity.Task;
import com.taskmanagement.backend.exception.ResourceNotFoundException;
import com.taskmanagement.backend.repository.ProjectRepository;
import com.taskmanagement.backend.repository.TaskRepository;
import com.taskmanagement.backend.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;

    @Override
    public ProjectDto create(CreateProjectRequest request) {
        Project project = Project.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();

        return modelMapper.map(projectRepository.save(project), ProjectDto.class);
    }

    @Override
    public List<ProjectDto> getAll() {
        return projectRepository.findAll()
                .stream()
                .map(project -> modelMapper.map(project, ProjectDto.class))
                .toList();
    }

    @Override
    public ProjectDetailsDto getById(UUID id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));

        List<Task> projectTasks = taskRepository.findByProjectId(id);

        List<String> currentMembers = projectTasks.stream()
                .map(task -> task.getAssignee().getFirstName() + " " + task.getAssignee().getLastName())
                .distinct()
                .toList();

        Map<String, Long> tasksByPriority = projectTasks.stream()
                .collect(Collectors.groupingBy(
                        task -> task.getPriority().name(),
                        Collectors.counting()
                ));

        return ProjectDetailsDto.builder()
                .id(project.getId())
                .name(project.getName())
                .description(project.getDescription())
                .createdAt(project.getCreatedAt())
                .currentMembers(currentMembers)
                .tasksByPriority(tasksByPriority)
                .build();
    }
}
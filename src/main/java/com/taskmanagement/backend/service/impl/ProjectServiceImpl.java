package com.taskmanagement.backend.service.impl;

import com.taskmanagement.backend.dto.CreateProjectRequest;
import com.taskmanagement.backend.dto.ProjectDetailsDto;
import com.taskmanagement.backend.dto.ProjectDto;
import com.taskmanagement.backend.entity.Project;
import com.taskmanagement.backend.exception.ResourceNotFoundException;
import com.taskmanagement.backend.repository.ProjectRepository;
import com.taskmanagement.backend.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Override
    public ProjectDto create(CreateProjectRequest request) {
        Project project = Project.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();

        Project savedProject = projectRepository.save(project);
        return mapToDto(savedProject);
    }

    @Override
    public List<ProjectDto> getAll() {
        return projectRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    @Override
    public ProjectDetailsDto getById(UUID id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + id));

        return ProjectDetailsDto.builder()
                .id(project.getId())
                .name(project.getName())
                .description(project.getDescription())
                .createdAt(project.getCreatedAt())
                .currentMembers(List.of())
                .tasksByPriority(new HashMap<>())
                .build();
    }

    private ProjectDto mapToDto(Project project) {
        return ProjectDto.builder()
                .id(project.getId())
                .name(project.getName())
                .description(project.getDescription())
                .createdAt(project.getCreatedAt())
                .build();
    }
}
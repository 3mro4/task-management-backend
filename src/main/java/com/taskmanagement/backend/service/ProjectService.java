package com.taskmanagement.backend.service;

import com.taskmanagement.backend.dto.PageResponse;
import com.taskmanagement.backend.dto.project.CreateProjectRequest;
import com.taskmanagement.backend.dto.project.ProjectDetailsDto;
import com.taskmanagement.backend.dto.project.ProjectDto;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ProjectService {

    ProjectDto create(CreateProjectRequest request);

    PageResponse<ProjectDto> getAll(Pageable pageable);

    ProjectDetailsDto getById(UUID id);
}
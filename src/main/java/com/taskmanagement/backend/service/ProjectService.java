package com.taskmanagement.backend.service;

import com.taskmanagement.backend.dto.CreateProjectRequest;
import com.taskmanagement.backend.dto.ProjectDetailsDto;
import com.taskmanagement.backend.dto.ProjectDto;

import java.util.List;
import java.util.UUID;

public interface ProjectService {

    ProjectDto create(CreateProjectRequest request);

    List<ProjectDto> getAll();

    ProjectDetailsDto getById(UUID id);
}
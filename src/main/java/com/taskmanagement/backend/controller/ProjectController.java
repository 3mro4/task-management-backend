package com.taskmanagement.backend.controller;

import com.taskmanagement.backend.dto.project.CreateProjectRequest;
import com.taskmanagement.backend.dto.project.ProjectDetailsDto;
import com.taskmanagement.backend.dto.project.ProjectDto;
import com.taskmanagement.backend.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ProjectController {

    public static final String BASE_URL = "/api/v1/projects";
    public static final String BASE_URL_WITH_ID = BASE_URL + "/{id}";

    private final ProjectService projectService;

    @PostMapping(BASE_URL)
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectDto createProject(@Valid @RequestBody CreateProjectRequest request) {
        return projectService.create(request);
    }

    @GetMapping(BASE_URL)
    public Page<ProjectDto> getAllProjects(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return projectService.getAll(page, size);
    }

    @GetMapping(BASE_URL_WITH_ID)
    public ProjectDetailsDto getProjectById(@PathVariable UUID id) {
        return projectService.getById(id);
    }
}
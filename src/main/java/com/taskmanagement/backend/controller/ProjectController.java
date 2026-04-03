package com.taskmanagement.backend.controller;

import com.taskmanagement.backend.dto.PageResponse;
import com.taskmanagement.backend.dto.project.CreateProjectRequest;
import com.taskmanagement.backend.dto.project.ProjectDetailsDto;
import com.taskmanagement.backend.dto.project.ProjectDto;
import com.taskmanagement.backend.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    public static final String BASE_URL = "/api/v1/projects";
    public static final String BASE_URL_WITH_ID = BASE_URL + "/{id}";

    // Create a new project ------------------------------------------------------------------
    @PostMapping(BASE_URL)
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectDto createProject(@Valid @RequestBody CreateProjectRequest request) {
        return projectService.create(request);
    }

    // Get all projects with pagination ------------------------------------------------------
    @GetMapping(BASE_URL)
    public PageResponse<ProjectDto> getAllProjects(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return projectService.getAll(pageable);
    }

    // Get project details by ID --------------------------------------------------------------
    @GetMapping(BASE_URL_WITH_ID)
    public ProjectDetailsDto getProjectById(@PathVariable UUID id) {
        return projectService.getById(id);
    }


    // Delete project by id -------------------------------------------------------------------
    @DeleteMapping(BASE_URL_WITH_ID)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProjectById(@PathVariable UUID id) {
        projectService.deleteById(id);

    }
}
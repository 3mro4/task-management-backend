package com.taskmanagement.backend.controller;

import com.taskmanagement.backend.dto.project.CreateProjectRequest;
import com.taskmanagement.backend.dto.project.ProjectDetailsDto;
import com.taskmanagement.backend.dto.project.ProjectDto;
import com.taskmanagement.backend.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    public static final String BASE_URL = "/api/v1/projects";
    public static final String BASE_URL_WITH_ID = BASE_URL + "/{id}";

    @PostMapping(BASE_URL)
    public ResponseEntity<ProjectDto> createProject(@Valid @RequestBody CreateProjectRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(projectService.create(request));
    }

    @GetMapping(BASE_URL)
    public ResponseEntity<List<ProjectDto>> getAllProjects() {
        return ResponseEntity.ok(projectService.getAll());
    }

    @GetMapping(BASE_URL_WITH_ID)
    public ResponseEntity<ProjectDetailsDto> getProjectById(@PathVariable UUID id) {
        return ResponseEntity.ok(projectService.getById(id));
    }
}
package com.taskmanagement.backend.controller;

import com.taskmanagement.backend.dto.CreateProjectRequest;
import com.taskmanagement.backend.dto.ProjectDetailsDto;
import com.taskmanagement.backend.dto.ProjectDto;
import com.taskmanagement.backend.service.impl.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/projects")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<ProjectDto> createProject(@Valid @RequestBody CreateProjectRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(projectService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<ProjectDto>> getAllProjects() {
        return ResponseEntity.ok(projectService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDetailsDto> getProjectById(@PathVariable UUID id) {
        return ResponseEntity.ok(projectService.getById(id));
    }
}
package com.taskmanagement.backend.service.impl;

import com.taskmanagement.backend.dto.PageResponse;
import com.taskmanagement.backend.dto.task.CreateTaskRequest;
import com.taskmanagement.backend.dto.task.TaskDto;
import com.taskmanagement.backend.dto.task.UpdateTaskRequest;
import com.taskmanagement.backend.entity.Project;
import com.taskmanagement.backend.entity.Task;
import com.taskmanagement.backend.entity.User;
import com.taskmanagement.backend.exception.ResourceNotFoundException;
import com.taskmanagement.backend.repository.ProjectRepository;
import com.taskmanagement.backend.repository.TaskRepository;
import com.taskmanagement.backend.repository.UserRepository;
import com.taskmanagement.backend.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public PageResponse<TaskDto> getAll(Pageable pageable) {
        Page<Task> page = taskRepository.findAll(pageable);
        return PageResponse.<TaskDto>builder()
                .content(page.map(this::mapToDto).toList())
                .currentPage(page.getNumber())
                .pageSize(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .lastPage(page.isLast())
                .build();
    }

    @Override
    public TaskDto getById(UUID id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
        return mapToDto(task);
    }

    @Override
    public TaskDto create(CreateTaskRequest request) {
        Project project = projectRepository.findById(request.getProjectId())
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + request.getProjectId()));
        User assignee = userRepository.findById(request.getAssigneeId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + request.getAssigneeId()));
        Task task = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .priority(request.getPriority())
                .status(request.getStatus())
                .dueDate(request.getDueDate())
                .project(project)
                .assignee(assignee)
                .build();
        return mapToDto(taskRepository.save(task));
    }

    @Override
    public TaskDto update(UUID id, UpdateTaskRequest request) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));

        task.setDescription(request.getDescription());
        task.setPriority(request.getPriority());
        task.setStatus(request.getStatus());
        task.setDueDate(request.getDueDate());

        return mapToDto(taskRepository.save(task));
    }

    @Override
    public void delete(UUID id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
        taskRepository.delete(task);
    }

    private TaskDto mapToDto(Task task) {
        TaskDto dto = modelMapper.map(task, TaskDto.class);
        dto.setProjectId(task.getProject().getId());
        dto.setProjectName(task.getProject().getName());
        dto.setAssigneeId(task.getAssignee().getId());
        dto.setAssigneeName(task.getAssignee().getFirstName() + " " + task.getAssignee().getLastName());
        return dto;
    }
}
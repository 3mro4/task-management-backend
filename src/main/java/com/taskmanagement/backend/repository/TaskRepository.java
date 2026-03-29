package com.taskmanagement.backend.repository;

import com.taskmanagement.backend.entity.Priority;
import com.taskmanagement.backend.entity.Status;
import com.taskmanagement.backend.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {

    List<Task> findByAssigneeId(UUID assigneeId);

    List<Task> findByProjectId(UUID projectId);

    long countByStatus(Status status);

    long countByPriority(Priority priority);
}
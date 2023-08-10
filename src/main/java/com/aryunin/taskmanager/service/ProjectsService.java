package com.aryunin.taskmanager.service;

import com.aryunin.taskmanager.entity.Project;
import org.springframework.data.domain.PageRequest;

public interface ProjectsService {
    Iterable<Project> getAll(PageRequest pageRequest);
    Project getById(int id);
    Project create(Project newProject);
    Project update(int id, Project newProject);
    Project delete(int id);
    Project assignTask(int projectId, int taskId);
    Project releaseTask(int projectId, int taskId);
}

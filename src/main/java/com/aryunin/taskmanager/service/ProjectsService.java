package com.aryunin.taskmanager.service;

import com.aryunin.taskmanager.entity.Project;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface ProjectsService {
    List<Project> getAll(PageRequest pageRequest);
    Project getById(int id);
    Project create(Project newProject);
    Project update(int id, Project newProject);
    Project delete(int id);
    Project assignTask(int projectId, int taskId);
    Project releaseTask(int projectId, int taskId);
}

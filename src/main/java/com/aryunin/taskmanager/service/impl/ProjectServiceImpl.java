package com.aryunin.taskmanager.service.impl;

import com.aryunin.taskmanager.entity.Project;
import com.aryunin.taskmanager.repository.ProjectRepository;
import com.aryunin.taskmanager.repository.TaskRepository;
import com.aryunin.taskmanager.service.ProjectsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectsService {
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;

    @Override
    public List<Project> getAll(PageRequest pageRequest) {
        return projectRepository.findAll(pageRequest).getContent();
    }

    @Override
    public Project getById(int id) {
        return projectRepository.findById(id).orElseThrow(() -> new RuntimeException("incorrect id")); // todo exc
    }

    @Override
    @Transactional
    public Project create(Project newProject) {
        newProject.setCreationDate(LocalDate.now());
        return projectRepository.save(newProject);
    }

    @Override
    @Transactional
    public Project update(int id, Project newProject) {
        var found = getById(id);
        newProject.setId(id);
        newProject.setCreationDate(found.getCreationDate());
        return projectRepository.save(newProject);
    }

    @Override
    @Transactional
    public Project delete(int id) {
        var found = getById(id);
        projectRepository.delete(found);
        return found;
    }

    @Override
    @Transactional
    public Project assignTask(int projectId, int taskId) {
        var task = taskRepository.getReferenceById(taskId);
        var project = projectRepository.getReferenceById(projectId);

        // todo exc
        task.setProject(project);
        project.getTasks().add(task);

        return project;
    }

    @Override
    @Transactional
    public Project releaseTask(int projectId, int taskId) {
        var task = taskRepository.getReferenceById(taskId);
        var project = projectRepository.getReferenceById(projectId);

        // todo exc
        task.setProject(null);
        project.getTasks().remove(task);

        return project;
    }
}

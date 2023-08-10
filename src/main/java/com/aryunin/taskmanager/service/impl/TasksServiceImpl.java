package com.aryunin.taskmanager.service.impl;

import com.aryunin.taskmanager.entity.Task;
import com.aryunin.taskmanager.repository.EmployeeRepository;
import com.aryunin.taskmanager.repository.TaskRepository;
import com.aryunin.taskmanager.service.TasksService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TasksServiceImpl implements TasksService {
    private final TaskRepository taskRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public List<Task> getAll(PageRequest pageRequest) {
        return taskRepository.findAll(pageRequest).getContent();
    }

    @Override
    public Task getById(int id) {
        return taskRepository.findById(id).orElseThrow(() -> new RuntimeException("incorrect id")); // todo exc
    }

    @Override
    @Transactional
    public Task create(Task newTask) {
        newTask.setCreationDate(LocalDate.now());
        return taskRepository.save(newTask);
    }

    @Override
    @Transactional
    public Task update(int id, Task newTask) {
        var found = getById(id);
        newTask.setId(id);
        newTask.setCreationDate(found.getCreationDate());
        return taskRepository.save(newTask);
    }

    @Override
    @Transactional
    public Task delete(int id) {
        var found = getById(id);
        taskRepository.delete(found);
        return found;
    }

    @Override
    @Transactional
    public Task assignEmployee(int taskId, int employeeId) {
        var task = taskRepository.getReferenceById(taskId);
        var employee = employeeRepository.getReferenceById(employeeId);

        // todo exc
        task.getEmployees().add(employee);
        employee.getTasks().add(task);

        return task;
    }

    @Override
    @Transactional
    public Task releaseEmployee(int taskId, int employeeId) {
        var task = taskRepository.getReferenceById(taskId);
        var employee = employeeRepository.getReferenceById(employeeId);

        // todo exc
        task.getEmployees().remove(employee);
        employee.getTasks().remove(task);

        return task;
    }
}

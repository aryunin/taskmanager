package com.aryunin.taskmanager.service;

import com.aryunin.taskmanager.entity.Task;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface TasksService {
    List<Task> getAll(PageRequest pageRequest);
    Task getById(int id);
    Task create(Task newTask);
    Task update(int id, Task newTask);
    Task delete(int id);
    Task assignEmployee(int taskId, int employeeId);
    Task releaseEmployee(int taskId, int employeeId);
}

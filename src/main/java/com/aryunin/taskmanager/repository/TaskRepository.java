package com.aryunin.taskmanager.repository;

import com.aryunin.taskmanager.entity.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Integer> {
}

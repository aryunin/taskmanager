package com.aryunin.taskmanager.repository;

import com.aryunin.taskmanager.entity.Project;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<Project, Integer> {
}

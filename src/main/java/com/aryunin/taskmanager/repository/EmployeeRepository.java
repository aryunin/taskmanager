package com.aryunin.taskmanager.repository;

import com.aryunin.taskmanager.entity.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
}

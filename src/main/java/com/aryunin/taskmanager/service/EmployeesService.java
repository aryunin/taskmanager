package com.aryunin.taskmanager.service;

import com.aryunin.taskmanager.entity.Employee;
import org.springframework.data.domain.PageRequest;

public interface EmployeesService {
    Iterable<Employee> getAll(PageRequest pageRequest);
    Employee getById(int id);
    Employee create(Employee newEmployee);
    Employee update(int id, Employee newEmployee);
    Employee delete(int id);
    Employee assignTask(int taskId, int employeeId);
    Employee releaseTask(int taskId, int employeeId);
}

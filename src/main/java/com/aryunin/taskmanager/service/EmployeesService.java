package com.aryunin.taskmanager.service;

import com.aryunin.taskmanager.entity.Employee;
import org.springframework.data.domain.PageRequest;

public interface EmployeesService {
    Iterable<Employee> getAll(PageRequest pageRequest);
    Employee getById();
    Employee create(Employee newEmployee);
    Employee update(int id, Employee newEmployee);
    Employee delete(int id);
    Employee assignTask();
    Employee releaseTask();
}

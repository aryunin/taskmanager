package com.aryunin.taskmanager.service;

import com.aryunin.taskmanager.entity.Employee;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface EmployeesService {
    List<Employee> getAll(PageRequest pageRequest);
    Employee getById(int id);
    Employee create(Employee newEmployee);
    Employee update(int id, Employee newEmployee);
    Employee delete(int id);
}

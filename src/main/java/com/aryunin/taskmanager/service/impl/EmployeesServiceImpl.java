package com.aryunin.taskmanager.service.impl;

import com.aryunin.taskmanager.entity.Employee;
import com.aryunin.taskmanager.repository.EmployeeRepository;
import com.aryunin.taskmanager.service.EmployeesService;
import com.aryunin.taskmanager.service.TasksService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EmployeesServiceImpl implements EmployeesService {
    private final EmployeeRepository repository;

    @Override
    public Iterable<Employee> getAll(PageRequest pageRequest) {
        return repository.findAll(pageRequest);
    }

    @Override
    public Employee getById(int id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("incorrect id")); // todo exc
    }

    @Override
    @Transactional
    public Employee create(Employee newEmployee) {
        return repository.save(newEmployee);
    }

    @Override
    @Transactional
    public Employee update(int id, Employee newEmployee) {
        getById(id);
        newEmployee.setId(id);
        return repository.save(newEmployee);
    }

    @Override
    @Transactional
    public Employee delete(int id) {
        var found = getById(id);
        repository.delete(found);
        return found;
    }
}

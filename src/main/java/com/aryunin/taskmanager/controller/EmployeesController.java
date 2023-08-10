package com.aryunin.taskmanager.controller;

import com.aryunin.taskmanager.dto.EmployeeDTO;
import com.aryunin.taskmanager.entity.Employee;
import com.aryunin.taskmanager.service.EmployeesService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("employees")
@RequiredArgsConstructor
public class EmployeesController {
    private final EmployeesService employeesService;
    private final ModelMapper modelMapper;

    @GetMapping("/{id}")
    public EmployeeDTO getByID(@PathVariable int id) {
        var res = employeesService.getById(id);
        return modelMapper.map(res, EmployeeDTO.class);
    }

    @GetMapping
    public List<EmployeeDTO> getAll(@RequestParam int page,
                                   @RequestParam int countPerPage,
                                   @RequestParam boolean sortByDate) {
        PageRequest pageRequest = (sortByDate) ?
                PageRequest.of(page, countPerPage, Sort.by("creationDate")) :
                PageRequest.of(page, countPerPage);

        return employeesService.getAll(pageRequest)
                .stream()
                .map(v -> modelMapper.map(v, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping
    public String createProject(@RequestBody EmployeeDTO employeeDTO) {
        Employee e = employeesService.create(modelMapper.map(employeeDTO, Employee.class));
        return "Employee " + e.getId() + " has been created";
    }

    @PutMapping("/{id}")
    public String updateProject(@PathVariable int id,
                                @RequestBody EmployeeDTO employeeDTO) {
        Employee e = employeesService.update(id, modelMapper.map(employeeDTO, Employee.class));
        return "Employee " + e.getId() + " has been updated";
    }

    @DeleteMapping("/{id}")
    public String deleteProject(@PathVariable int id) {
        employeesService.delete(id);
        return "Employee " + id + " has been deleted";
    }
}

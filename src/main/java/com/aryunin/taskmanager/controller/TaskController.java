package com.aryunin.taskmanager.controller;

import com.aryunin.taskmanager.dto.TaskDTO;
import com.aryunin.taskmanager.entity.Task;
import com.aryunin.taskmanager.service.TasksService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {
    private final TasksService tasksService;
    private final ModelMapper modelMapper;

    @GetMapping("/{id}")
    public TaskDTO getByID(@PathVariable int id) {
        var res = tasksService.getById(id);
        return modelMapper.map(res, TaskDTO.class);
    }

    @GetMapping
    public List<TaskDTO> getAll(@RequestParam int page,
                                   @RequestParam int countPerPage,
                                   @RequestParam boolean sortByDate) {
        PageRequest pageRequest = (sortByDate) ?
                PageRequest.of(page, countPerPage, Sort.by("creationDate")) :
                PageRequest.of(page, countPerPage);

        return tasksService.getAll(pageRequest)
                .stream()
                .map(v -> modelMapper.map(v, TaskDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping
    public String createTask(@RequestBody TaskDTO taskDTO) {
        Task p = tasksService.create(modelMapper.map(taskDTO, Task.class));
        return "Task " + p.getId() + " has been created";
    }

    @PutMapping("/{id}")
    public String updateTask(@PathVariable int id,
                                @RequestBody TaskDTO taskDTO) {
        Task p = tasksService.update(id, modelMapper.map(taskDTO, Task.class));
        return "Task " + p.getId() + " has been updated";
    }

    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable int id) {
        tasksService.delete(id);
        return "Task " + id + " has been deleted";
    }

    @PostMapping("/{taskId}/assign/{employeeId}")
    public String assignEmployee(@PathVariable int taskId,
                             @PathVariable int employeeId) {
        var task = tasksService.assignEmployee(taskId, employeeId);
        return "Task " + task.getId() + " has been updated";
    }

    @PostMapping("/{taskId}/release/{employeeId}")
    public String releaseEmployee(@PathVariable int taskId,
                              @PathVariable int employeeId) {
        var task = tasksService.releaseEmployee(taskId, employeeId);
        return "Task " + task.getId() + " has been updated";
    }
}

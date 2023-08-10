package com.aryunin.taskmanager.controller;

import com.aryunin.taskmanager.dto.ProjectDTO;
import com.aryunin.taskmanager.entity.Project;
import com.aryunin.taskmanager.service.ProjectsService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectsService projectsService;
    private final ModelMapper modelMapper;

    @GetMapping("/{id}")
    public ProjectDTO getByID(@PathVariable int id) {
        var res = projectsService.getById(id);
        return modelMapper.map(res, ProjectDTO.class);
    }

    @GetMapping
    public List<ProjectDTO> getAll(@RequestParam int page,
                                   @RequestParam int countPerPage,
                                   @RequestParam boolean sortByDate) {
        PageRequest pageRequest = (sortByDate) ?
                PageRequest.of(page, countPerPage, Sort.by("creationDate")) :
                PageRequest.of(page, countPerPage);

        return projectsService.getAll(pageRequest)
                .stream()
                .map(v -> modelMapper.map(v, ProjectDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping
    public String createProject(@RequestBody ProjectDTO projectDTO) {
        Project p = projectsService.create(modelMapper.map(projectDTO, Project.class));
        return "Project " + p.getId() + " has been created";
    }

    @PutMapping("/{id}")
    public String updateProject(@PathVariable int id,
                                @RequestBody ProjectDTO projectDTO) {
        Project p = projectsService.update(id, modelMapper.map(projectDTO, Project.class));
        return "Project " + p.getId() + " has been updated";
    }

    @DeleteMapping("/{id}")
    public String deleteProject(@PathVariable int id) {
        projectsService.delete(id);
        return "Project " + id + " has been deleted";
    }
}

package ru.zagorodnikova.tm.taskmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ru.zagorodnikova.tm.taskmanager.dto.ProjectDto;
import ru.zagorodnikova.tm.taskmanager.entity.Project;
import ru.zagorodnikova.tm.taskmanager.repository.ProjectDtoRepository;
import ru.zagorodnikova.tm.taskmanager.repository.ProjectRepository;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectDtoRepository projectDtoRepository;

    @GetMapping(value = "/projects", produces = "application/json")
    public Page find(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int limit) {
        Pageable pageable = PageRequest.of(page - 1, limit);
        return projectRepository.findAll(pageable);
    }

    @PostMapping(value = "/projects/merge", produces = "application/json", consumes = "application/json")
    public List<ProjectDto> merge(@RequestBody List<ProjectDto> projects) {
        return projectDtoRepository.saveAll(projects);
    }

    @PostMapping(value = "/projects/delete", produces = "application/json")
    public void delete(@RequestBody List<Project> projects) {
        projectRepository.deleteAll(projects);
    }
}

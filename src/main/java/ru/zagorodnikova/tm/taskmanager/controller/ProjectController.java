package ru.zagorodnikova.tm.taskmanager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.zagorodnikova.tm.taskmanager.dto.ProjectDto;
import ru.zagorodnikova.tm.taskmanager.entity.CustomUserDetails;
import ru.zagorodnikova.tm.taskmanager.entity.Project;
import ru.zagorodnikova.tm.taskmanager.entity.Sort;
import ru.zagorodnikova.tm.taskmanager.repository.ProjectDtoRepository;
import ru.zagorodnikova.tm.taskmanager.repository.ProjectRepository;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectDtoRepository projectDtoRepository;

    @GetMapping(value = "/projects", produces = "application/json")
    public Page find(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int limit, @RequestParam(required = false, name = "sort") String param) throws IOException {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Pageable pageable;
        if (param == null) {
            pageable = PageRequest.of(page - 1, limit);
        } else {
            @NotNull final ObjectMapper mapper = new ObjectMapper();
            Sort[] sort = mapper.readValue(param, Sort[].class);
            System.out.println(sort[0]);
            pageable = PageRequest.of(page - 1, limit, sort[0].getDirection(), sort[0].getProperty());
        }
        return projectRepository.findAllByUserId(userDetails.getUser().getId(), pageable);
    }

    @PostMapping(value = "/projects/merge", produces = "application/json", consumes = "application/json")
    public List<ProjectDto> merge(@RequestBody List<ProjectDto> projects) {
        return projectDtoRepository.saveAll(projects);
    }

    @PostMapping(value = "/projects/delete", consumes = "application/json")
    public void delete(@RequestBody List<Project> projects) {
        projectRepository.deleteAll(projects);
    }

}

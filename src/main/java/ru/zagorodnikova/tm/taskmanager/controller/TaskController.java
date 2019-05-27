package ru.zagorodnikova.tm.taskmanager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.zagorodnikova.tm.taskmanager.entity.CustomUserDetails;
import ru.zagorodnikova.tm.taskmanager.entity.Sort;
import ru.zagorodnikova.tm.taskmanager.entity.Task;
import ru.zagorodnikova.tm.taskmanager.repository.TaskRepository;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping(value = "/tasks", produces = "application/json")
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
        return taskRepository.findAllByUserId(userDetails.getUser().getId(), pageable);
    }

    @PostMapping(value = "/tasks/merge", produces = "application/json", consumes = "application/json")
    private List<Task> merge(@RequestBody List<Task> tasks) {
        return taskRepository.saveAll(tasks);
    }

    @PostMapping(value = "/tasks/delete", consumes = "application/json")
    private void delete(@RequestBody List<Task> tasks) {
        taskRepository.deleteAll(tasks);
    }
}

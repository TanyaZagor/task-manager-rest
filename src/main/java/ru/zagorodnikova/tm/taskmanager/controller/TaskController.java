package ru.zagorodnikova.tm.taskmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ru.zagorodnikova.tm.taskmanager.entity.Task;
import ru.zagorodnikova.tm.taskmanager.repository.TaskRepository;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping(value = "/tasks", produces = "application/json")
    public Page find(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int limit) {
        Pageable pageable = PageRequest.of(page - 1, limit);
        return taskRepository.findAll(pageable);
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

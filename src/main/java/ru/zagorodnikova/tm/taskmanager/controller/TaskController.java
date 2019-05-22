package ru.zagorodnikova.tm.taskmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
    private List<Task> findAll() {
        return taskRepository.findAll();
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
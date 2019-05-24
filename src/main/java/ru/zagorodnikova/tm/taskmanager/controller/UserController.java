package ru.zagorodnikova.tm.taskmanager.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.zagorodnikova.tm.taskmanager.entity.CustomUserDetails;
import ru.zagorodnikova.tm.taskmanager.entity.User;

@RestController
@RequestMapping("/api")
public class UserController {

    @GetMapping(value = "/user", produces = "application/json")
    private User findOne() {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUser();
    }

}

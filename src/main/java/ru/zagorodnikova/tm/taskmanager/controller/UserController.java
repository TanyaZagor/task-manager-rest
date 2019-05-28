package ru.zagorodnikova.tm.taskmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.zagorodnikova.tm.taskmanager.entity.CustomUserDetails;
import ru.zagorodnikova.tm.taskmanager.entity.User;
import ru.zagorodnikova.tm.taskmanager.repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping(value = "/user", produces = "application/json")
    private User findOne() {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUser();
    }

    @PostMapping(value = "/signUp", consumes = "application/json")
    private void signUp(@RequestBody List<User> users) {
        User user = users.get(0);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

}

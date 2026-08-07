package com.Terminator.taskmanager.controller;


import com.Terminator.taskmanager.dto.UserRequestDTO;
import com.Terminator.taskmanager.dto.UserResponseDTO;
import com.Terminator.taskmanager.entity.User;
import com.Terminator.taskmanager.service.UserService;
import jakarta.validation.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @PostMapping
    public UserResponseDTO register(@Valid @RequestBody UserRequestDTO request)
    {
        return userService.register(request);
    }

    @GetMapping
    public List<User> getAllUsers()
    {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id)
    {
        return userService.getUser(id);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id)
    {
       userService.deleteUser(id);
        return "User deleted";
    }

}

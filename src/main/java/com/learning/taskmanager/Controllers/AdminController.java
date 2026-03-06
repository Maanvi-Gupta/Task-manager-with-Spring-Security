package com.learning.taskmanager.Controllers;

import com.learning.taskmanager.Entity.Task;
import com.learning.taskmanager.Repository.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/tasks")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Task> getAllTasks() {

        return taskRepository.findAll();
    }
}
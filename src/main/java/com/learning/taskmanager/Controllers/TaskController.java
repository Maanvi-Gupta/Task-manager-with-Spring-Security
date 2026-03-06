package com.learning.taskmanager.Controllers;

import com.learning.taskmanager.Dto.TaskDTO;
import com.learning.taskmanager.Entity.Task;
import com.learning.taskmanager.Service.TaskService;

// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public Task createTask(@RequestBody TaskDTO dto) {

        return taskService.createTask(dto);
    }

    @GetMapping
    public List<Task> getTasks() {

        return taskService.getTasks(1L);
    }
}
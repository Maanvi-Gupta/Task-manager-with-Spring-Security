package com.learning.taskmanager.Controllers;

import com.learning.taskmanager.Dto.TaskDTO;
import com.learning.taskmanager.Entity.Task;
import com.learning.taskmanager.Service.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    // Create Task
    @PostMapping
    public Task createTask(@RequestBody TaskDTO dto) {
        return taskService.createTask(dto);
    }

    // Get all tasks for logged-in user
    @GetMapping
    public List<Task> getTasks() {
        return taskService.getTasks();
    }

    // Get single task
    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    // Update task
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody TaskDTO dto) {
        return taskService.updateTask(id, dto);
    }

    // Delete task
    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "Task deleted successfully";
    }

    // Mark task as completed
    @PatchMapping("/{id}/complete")
    public Task markTaskCompleted(@PathVariable Long id) {
        return taskService.markTaskCompleted(id);
    }
}
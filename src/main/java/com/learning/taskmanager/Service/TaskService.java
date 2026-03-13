package com.learning.taskmanager.Service;

import com.learning.taskmanager.Dto.TaskDTO;
import com.learning.taskmanager.Entity.Task;
import com.learning.taskmanager.Entity.User;
import com.learning.taskmanager.Repository.TaskRepository;
import com.learning.taskmanager.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    // Create Task
    public Task createTask(TaskDTO dto) {

        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Task task = new Task();
        task.setTitle(dto.title);
        task.setDescription(dto.description);
        task.setStatus(dto.status);
        task.setCreatedAt(LocalDateTime.now());
        task.setUser(user);

        return taskRepository.save(task);
    }

    // Get all tasks of logged-in user
    public List<Task> getTasks() {

        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return taskRepository.findByUserUsername(username);
    }

    // Get single task
    public Task getTaskById(Long id) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        validateTaskOwner(task);

        return task;
    }

    // Update task
    public Task updateTask(Long id, TaskDTO dto) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        validateTaskOwner(task);

        task.setTitle(dto.title);
        task.setDescription(dto.description);
        task.setStatus(dto.status);

        return taskRepository.save(task);
    }

    // Delete task
    public void deleteTask(Long id) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        validateTaskOwner(task);

        taskRepository.delete(task);
    }

    // Mark task complete
    public Task markTaskCompleted(Long id) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        validateTaskOwner(task);

        task.setStatus("COMPLETED");

        return taskRepository.save(task);
    }

    // Security check
    private void validateTaskOwner(Task task) {

        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        if (!task.getUser().getUsername().equals(username)) {
            throw new RuntimeException("Unauthorized access to task");
        }
    }
}
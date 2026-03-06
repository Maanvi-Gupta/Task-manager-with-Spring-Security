// package com.learning.taskmanager.Service;

// import com.learning.taskmanager.Dto.TaskDTO;
// import com.learning.taskmanager.Entity.Task;
// import com.learning.taskmanager.Repository.TaskRepository;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.stereotype.Service;

// import java.time.LocalDateTime;
// import java.util.List;

// @Service
// public class TaskService {

//     @Autowired
//     private TaskRepository taskRepository;

//     public Task createTask(TaskDTO dto) {

//         String username = SecurityContextHolder.getContext().getAuthentication().getName();

//         Task task = new Task();

//         task.setTitle(dto.title);
//         task.setDescription(dto.description);
//         task.setStatus(dto.status);
//         task.setCreatedAt(LocalDateTime.now());

//         task.setUserId(1L);

//         return taskRepository.save(task);
//     }

//     public List<Task> getTasks(Long userId) {

//         return taskRepository.findByUserId(userId);
//     }
//     public List<Task> getTasksByUsername(String username) {

//     return taskRepository.findByUserUsername(username);
// }
// }
package com.learning.taskmanager.Service;

import com.learning.taskmanager.Dto.TaskDTO;
import com.learning.taskmanager.Entity.Task;
import com.learning.taskmanager.Repository.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(TaskDTO dto) {

        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        // TEMPORARY logic
        // Until you fetch userId from DB
        Long userId = 1L;

        Task task = new Task();

        task.setTitle(dto.title);
        task.setDescription(dto.description);
        task.setStatus(dto.status);
        task.setCreatedAt(LocalDateTime.now());

        task.setUserId(userId);

        return taskRepository.save(task);
    }

    public List<Task> getTasks(Long userId) {
        return taskRepository.findByUserId(userId);
    }
}
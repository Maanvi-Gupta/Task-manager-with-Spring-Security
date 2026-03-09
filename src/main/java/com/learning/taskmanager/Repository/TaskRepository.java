package com.learning.taskmanager.Repository;

import com.learning.taskmanager.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByUserId(Long userId);

    List<Task> findByUserUsername(String username);

}
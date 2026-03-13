package com.learning.taskmanager.Controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;

// @GetMapping("/api/admin/tasks")
// @PreAuthorize("hasRole('ADMIN')")
public class ModificationsController {
    
public String adminEndpoint() {
    return "This is an admin endpoint.";

}
}
    

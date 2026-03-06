package com.learning.taskmanager.Controllers;

import org.springframework.web.bind.annotation.GetMapping;

public class TestController {
    @GetMapping("/test")
      public String test() {
    return "API secured";
}
}

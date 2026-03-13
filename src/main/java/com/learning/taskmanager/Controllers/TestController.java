package com.learning.taskmanager.Controllers;

import org.springframework.web.bind.annotation.*;


@RestController
public class TestController {
    @GetMapping("/test")
      public String test() {
    return "API secured";
}
}

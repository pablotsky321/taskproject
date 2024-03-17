package com.tasks.taskproject.security.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/user_page")
    public String user_page(){
        return "hello from user page";
    }

    @GetMapping("/admin_page")
    public String admin_page(){
        return "hello from admin page";
    }

}

package com.tasks.taskproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tasks.taskproject.entities.Task;
import com.tasks.taskproject.services.TaskService;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    TaskService taskService;

    @PutMapping("/add/{token}")
    public ResponseEntity<?> addTask(@RequestBody Task task,@PathVariable("token") String token){
        try {
            return new ResponseEntity<Task>(taskService.addTask(token, task),HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_GATEWAY);
        }
    }

}

package com.tasks.taskproject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tasks.taskproject.entities.Task;
import com.tasks.taskproject.services.TaskService;

@RestController
@RequestMapping("/task")
@CrossOrigin("http://localhost:4200")
public class TaskController {

    @Autowired
    TaskService taskService;

    @PutMapping("/add/{id_user}")
    public ResponseEntity<?> addTask(@RequestBody Task task,@PathVariable("id_user") String id_user){
        try {
            return new ResponseEntity<Task>(taskService.addTask(id_user, task),HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_GATEWAY);
        }
    }

    @GetMapping("/user_tasks/{id_user}")
    public ResponseEntity<?> showTasks(@PathVariable("id_user") String id_user){
        try {
            return new ResponseEntity<List<Task>>(taskService.showTasks(id_user),HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_GATEWAY);
        }
    }

}

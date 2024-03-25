package com.tasks.taskproject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tasks.taskproject.entities.Task;
import com.tasks.taskproject.reponse.TaskResponse;
import com.tasks.taskproject.requests.FinishedRe;
import com.tasks.taskproject.services.TaskService;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/task")
@CrossOrigin("http://localhost:4200")
public class TaskController {

    @Autowired
    TaskService taskService;

    @PostMapping("/add/{id_user}")
    public ResponseEntity<?> addTask(@RequestBody Task task,@PathVariable("id_user") String id_user){
        try {
            return new ResponseEntity<Task>(taskService.addTask(id_user, task),HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_GATEWAY);
        }
    }

    @GetMapping("/show/{id_user}")
    public ResponseEntity<?> showTasks(@PathVariable("id_user") String id_user){
        try {
            return new ResponseEntity<List<Task>>(taskService.showTasks(id_user),HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_GATEWAY);
        }
    }

    @DeleteMapping("/delete/{id_task}")
    public ResponseEntity<?> findTask(@PathVariable("id_task") String id_task){
        try {
            return new ResponseEntity<String>(taskService.destroyTask(id_task),HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_GATEWAY);
        }
    }

    @PutMapping("/update/{id_task}")
    public ResponseEntity<?> updateTask(@PathVariable("id_task") String id_task,@RequestBody Task taskInfo){
        try {
            return new ResponseEntity<Task>(taskService.updateTask(id_task, taskInfo),HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_GATEWAY);
        }
    }

    @GetMapping("/details/{id_task}")
    public ResponseEntity<?> seeTask(@PathVariable("id_task") String id_task){
        try {
            return  new ResponseEntity<Task>(taskService.seeTask(id_task),HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_GATEWAY);
        }
    }

    @PutMapping("/finished/{id_task}")
    public ResponseEntity<?> isFinished(@PathVariable("id_task") String id_task,@RequestBody FinishedRe finishedRe){
        try {
            boolean finished = taskService.isFinished(id_task, finishedRe.isFinished());
            if(finished){
                return new ResponseEntity<TaskResponse>(new TaskResponse("task finished"),HttpStatus.ACCEPTED);
            }else{
                return new ResponseEntity<TaskResponse>(new TaskResponse("task not finished"),HttpStatus.ACCEPTED);
            }
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_GATEWAY);
        }
    }

}

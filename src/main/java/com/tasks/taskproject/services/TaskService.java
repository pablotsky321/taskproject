package com.tasks.taskproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tasks.taskproject.entities.Estado;
import com.tasks.taskproject.entities.Task;
import com.tasks.taskproject.repositories.TaskRepository;
import com.tasks.taskproject.security.entities.UserEntity;
import com.tasks.taskproject.security.repositories.UserRepository;
import com.tasks.taskproject.security.services.JwtService;
@Service
public class TaskService {
    
    @Autowired
    TaskRepository taskRepository;

    @Autowired
    JwtService jwtService;

    @Autowired
    UserRepository userRepository;

    public Task addTask(String token,Task task){
        String username = jwtService.extractUsername(token);
        UserEntity userFind = userRepository.findByUsername(username).get();
        task.setEstado(Estado.ON_TIME);
        task.setUserEntity(userFind);
        Task taskSave = taskRepository.save(task);
        taskSave.setUserEntity(null);
        userFind.getTareas().add(taskSave);
        userRepository.save(userFind);
        return taskSave;
    }

}

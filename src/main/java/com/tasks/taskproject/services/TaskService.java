package com.tasks.taskproject.services;

import java.time.LocalDateTime;
import java.util.List;

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

    public Task addTask(String id_user,Task task){
        UserEntity userFind = userRepository.findById(id_user).get();
        task.setEstado(Estado.ON_TIME);
        task.setUserEntity(userFind);
        task.setFecha_creacion(LocalDateTime.now());
        Task taskSave = taskRepository.save(task);
        taskSave.setUserEntity(null);
        userFind.getTareas().add(taskSave);
        userRepository.save(userFind);
        return taskSave;
    }

    public List<Task> showTasks(String id_user){
        UserEntity userFind = userRepository.findById(id_user).get();
        return userFind.getTareas();
    }

}

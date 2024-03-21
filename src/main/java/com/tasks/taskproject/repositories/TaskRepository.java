package com.tasks.taskproject.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tasks.taskproject.entities.Task;
import java.util.List;

import com.tasks.taskproject.security.entities.UserEntity;


public interface TaskRepository extends MongoRepository<Task,String>{
    List<Task> findByUserEntity(UserEntity userEntity);
}

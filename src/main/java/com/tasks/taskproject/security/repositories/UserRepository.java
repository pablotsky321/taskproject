package com.tasks.taskproject.security.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tasks.taskproject.security.entities.UserEntity;
import java.util.Optional;


public interface UserRepository extends MongoRepository<UserEntity,String> {
    Optional<UserEntity> findByUsername(String username);
}

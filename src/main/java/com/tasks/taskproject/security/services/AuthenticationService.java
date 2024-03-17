package com.tasks.taskproject.security.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tasks.taskproject.entities.Task;
import com.tasks.taskproject.security.authRe.RegisterResponse;
import com.tasks.taskproject.security.entities.Roles;
import com.tasks.taskproject.security.entities.UserEntity;
import com.tasks.taskproject.security.repositories.UserRepository;

@Service
public class AuthenticationService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public RegisterResponse register(UserEntity user){
        if(userRepository.findByUsername(user.getUsername()).isPresent()){
            return new RegisterResponse(null,"user already exists");
        }
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        List<Task> tareas = new ArrayList<>();
        user.setRole(Roles.USER);
        authorities.add(new SimpleGrantedAuthority(Roles.USER.name()));
        authorities.add(new SimpleGrantedAuthority(Roles.INVITED.name()));
        user.setAuthorities(authorities);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setTareas(tareas);
        UserEntity userSave = userRepository.save(user);
        return new RegisterResponse(userSave,"user created");
    }

}

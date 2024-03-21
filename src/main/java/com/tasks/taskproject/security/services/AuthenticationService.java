package com.tasks.taskproject.security.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

//import com.tasks.taskproject.entities.Task;
import com.tasks.taskproject.security.authRe.LoginRequest;
import com.tasks.taskproject.security.authRe.LoginResponse;
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

    @Autowired
    JwtService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    public RegisterResponse register(UserEntity user){
        if(userRepository.findByUsername(user.getUsername()).isPresent()){
            return new RegisterResponse(null,"user already exists");
        }
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        //List<Task> tareas = new ArrayList<>();
        user.setRole(Roles.USER);
        authorities.add(new SimpleGrantedAuthority(Roles.USER.name()));
        authorities.add(new SimpleGrantedAuthority(Roles.INVITED.name()));
        user.setAuthorities(authorities);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserEntity userSave = userRepository.save(user);
        return new RegisterResponse(userSave,"user created");
    }

    public RegisterResponse registerAdmin(UserEntity user){
        if(userRepository.findByUsername(user.getUsername()).isPresent()){
            return new RegisterResponse(null,"user already exists");
        }
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
       // List<Task> tareas = new ArrayList<>();
        user.setRole(Roles.ADMIN);
        authorities.add(new SimpleGrantedAuthority(Roles.USER.name()));
        authorities.add(new SimpleGrantedAuthority(Roles.INVITED.name()));
        authorities.add(new SimpleGrantedAuthority(Roles.ADMIN.name()));
        user.setAuthorities(authorities);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserEntity userSave = userRepository.save(user);
        return new RegisterResponse(userSave,"user created");
    }

    public LoginResponse login(LoginRequest loginRequest){
        if(!userRepository.findByUsername(loginRequest.getUsername()).isPresent()){
            return new LoginResponse(null,null,"username not found");
        }
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        UserEntity user = userRepository.findByUsername(loginRequest.getUsername()).get();
        String token = jwtService.generateToken(user);
        return new LoginResponse(token, user.getId(),"Login successful");
    }

}

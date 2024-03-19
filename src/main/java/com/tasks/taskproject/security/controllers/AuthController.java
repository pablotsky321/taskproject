package com.tasks.taskproject.security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tasks.taskproject.security.authRe.LoginRequest;
import com.tasks.taskproject.security.authRe.LoginResponse;
import com.tasks.taskproject.security.authRe.RegisterResponse;
import com.tasks.taskproject.security.entities.UserEntity;
import com.tasks.taskproject.security.services.AuthenticationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserEntity user){
        try {
            return new ResponseEntity<RegisterResponse>(authenticationService.register(user),HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest){
        try {
            return new ResponseEntity<LoginResponse>(authenticationService.login(loginRequest),HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/register_admin")
    public ResponseEntity<?> registerAdmin(@Valid @RequestBody UserEntity user){
        try {
            return new ResponseEntity<RegisterResponse>(authenticationService.registerAdmin(user),HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(){
        try {
            return new ResponseEntity<String>("logout successful",HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST); 
        }
    }

}

package com.tasks.taskproject.security.entities;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.security.core.GrantedAuthority;

import com.tasks.taskproject.entities.Task;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Document(collection = "Users")
public class UserEntity {

    @MongoId
    private String id;
    @NotBlank
    private String nombres;
    @NotBlank
    private String apellidos;
    @Indexed
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    
    private Roles role;
    private List<GrantedAuthority> authorities;


    public UserEntity(String nombres, String apellidos, String username, String password, Roles role,
            List<GrantedAuthority> authorities, List<Task> tareas) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.username = username;
        this.password = password;
        this.role = role;
        this.authorities = authorities;
    }

    public UserEntity() {

    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public List<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public String getId() {
        return id;
    }

}

package com.tasks.taskproject.security.authRe;

import com.tasks.taskproject.security.entities.UserEntity;

public class RegisterResponse {

    private UserEntity userEntity;
    private String message;
    
    public RegisterResponse(UserEntity userEntity, String message) {
        this.userEntity = userEntity;
        this.message = message;
    }

    public RegisterResponse(){

    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}

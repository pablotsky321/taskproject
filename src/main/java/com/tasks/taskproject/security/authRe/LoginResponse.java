package com.tasks.taskproject.security.authRe;

public class LoginResponse {

    private String token;
    private String message;
    private String id_user;
    
    public LoginResponse(String token,String id_user ,String message) {
        this.token = token;
        this.message = message;
        this.id_user = id_user;
    }

    public LoginResponse(){
        
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

}

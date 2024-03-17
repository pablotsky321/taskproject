package com.tasks.taskproject.entities;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.tasks.taskproject.security.entities.UserEntity;

@Document(collection = "Tasks")
public class Task {

    @Id
    private String id;
    private String titulo;
    private String descripcion;
    private Date fecha_creacion;
    private Date fecha_finalizacion;
    private Estado estado;

    @DBRef
    private UserEntity userEntity;

    public Task(String titulo, String descripcion, Date fecha_creacion, Date fecha_finalizacion, Estado estado) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha_creacion = fecha_creacion;
        this.fecha_finalizacion = fecha_finalizacion;
        this.estado = estado;
    }

    public Task(String id, String titulo, String descripcion, Date fecha_creacion, Date fecha_finalizacion,
            Estado estado, UserEntity userEntity) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha_creacion = fecha_creacion;
        this.fecha_finalizacion = fecha_finalizacion;
        this.estado = estado;
        this.userEntity = userEntity;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public Task() {
    
    }

    public String getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public Date getFecha_finalizacion() {
        return fecha_finalizacion;
    }

    public void setFecha_finalizacion(Date fecha_finalizacion) {
        this.fecha_finalizacion = fecha_finalizacion;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

}

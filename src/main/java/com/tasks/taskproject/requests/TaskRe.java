package com.tasks.taskproject.requests;

public class TaskRe {
    private String id_task;

    public TaskRe(String id_task) {
        this.id_task = id_task;
    }

    public TaskRe(){

    }

    public String getId_task() {
        return id_task;
    }

    public void setId_task(String id_task) {
        this.id_task = id_task;
    }

}

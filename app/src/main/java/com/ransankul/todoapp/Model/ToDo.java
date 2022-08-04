package com.ransankul.todoapp.Model;

public class ToDo {

    public boolean isVisible;
    private String task, current, finalStatus;
    private int id;

    public ToDo(String task, String current, String finalStatus, int id, boolean isVisible) {
        this.task = task;
        this.current = current;
        this.finalStatus = finalStatus;
        this.id = id;
        this.isVisible = isVisible;
    }

    public ToDo(String task, String current, String finalStatus, int id) {
        this.task = task;
        this.current = current;
        this.finalStatus = finalStatus;
        this.id = id;
    }

    public ToDo() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String getFinalStatus() {
        return finalStatus;
    }

    public void setFinalStatus(String finalStatus) {
        this.finalStatus = finalStatus;
    }
}

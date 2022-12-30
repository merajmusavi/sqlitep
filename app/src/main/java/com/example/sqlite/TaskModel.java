package com.example.sqlite;

public class TaskModel {
    private long id;
    private String title;
    private boolean IsCompleted;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return IsCompleted;
    }

    public void setCompleted(boolean completed) {
        IsCompleted = completed;
    }
}

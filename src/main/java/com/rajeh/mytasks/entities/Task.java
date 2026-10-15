package com.rajeh.mytasks.entities;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;

import java.text.SimpleDateFormat;
import java.util.Date;


@Entity
@Table(name= "tasks")
@JsonPropertyOrder({"id", "title", "description", "status", "createdAt"})
public class Task {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 150)
    private String title;

    @Column(length = 500)
    private String description;

    @Column(name = "created_at",updatable = false)
    private String createdAt;
    
    @Column(name = "last_update_at")
    private String lastUpdateAt;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    public Task() {}

    public Task(String title, String description, boolean status) {
        this.title = title;
        this.description = description;
        this.status = TaskStatus.TODO;
    }

    @PrePersist
    protected void onCreate()
    {
        this.createdAt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
        this.lastUpdateAt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
    }

    @PreUpdate
    protected void onUpdate()
    {
        this.lastUpdateAt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
    }

    public int getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public TaskStatus getStatus() {
        return this.status ;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getLastUpdateAt() {
        return lastUpdateAt;
    }

    public void setLastUpdateAt(String lastUpdateAt) {
        this.lastUpdateAt = lastUpdateAt;
    }
}

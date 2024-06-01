package org.example.model.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class Task implements Repeatable {
    private long id;
    private String title;
    private String description;
    private TaskType type;
    private FrequencyType frequencyType;
    private LocalDateTime dateTime;

    public Task(long id, String title, String description, TaskType type, FrequencyType frequencyType, LocalDateTime dateTime) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.type = type;
        this.frequencyType = frequencyType;
        this.dateTime = dateTime;
    }

    public Task() {
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public FrequencyType getFrequencyType() {
        return frequencyType;
    }

    public void setFrequencyType(FrequencyType frequencyType) {
        this.frequencyType = frequencyType;
    }
}

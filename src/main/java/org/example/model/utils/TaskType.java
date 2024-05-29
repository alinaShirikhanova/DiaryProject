package org.example.model.utils;

public enum TaskType {
    PERSONAL("Личная"), WORKING("Рабочая");
    private final String name;

    TaskType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

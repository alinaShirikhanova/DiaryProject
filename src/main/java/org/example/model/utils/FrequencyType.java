package org.example.model.utils;

public enum FrequencyType {
    ONETIME("oneTime"),
    DAILY("daily"),
    WEEKLY("weekly"),
    MONTHLY("monthly"),
    ANNUAL("annual");
    private final String name;
    FrequencyType(String name) {
        this.name = name;
    }
}

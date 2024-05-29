package org.example.model.entity;

import org.example.model.utils.Task;
import org.example.model.utils.TaskType;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class OneTimeTask extends Task {

    public OneTimeTask(long id, String title, String description, TaskType type, LocalDateTime dateTime) {
        super(id, title, description, type, dateTime);
    }

    @Override
    public LocalDate getClosestDate(LocalDate currentDate) {
       return this.getDateTime().toLocalDate();
    }
}

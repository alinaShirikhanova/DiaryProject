package org.example.model.entity;

import org.example.model.utils.FrequencyType;
import org.example.model.utils.Repeatable;
import org.example.model.utils.Task;
import org.example.model.utils.TaskType;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class WeeklyTask extends Task implements Repeatable {
    public WeeklyTask(long id, String title, String description, TaskType type, FrequencyType frequencyType, LocalDateTime dateTime) {
        super(id, title, description, type, frequencyType, dateTime);
    }

    public WeeklyTask() {
    }

    @Override
    public LocalDate getClosestDate(LocalDate currentDate) {
        LocalDate taskDate = this.getDateTime().toLocalDate();
        while (taskDate.isBefore(currentDate)){
            taskDate = taskDate.plusWeeks(1);
        }
        return taskDate;
    }
}

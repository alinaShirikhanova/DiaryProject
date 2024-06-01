package org.example.model.entity;

import org.example.model.utils.Task;
import org.example.model.utils.TaskType;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DailyTask extends Task {
    public DailyTask(long id, String title, String description, TaskType type, LocalDateTime dateTime) {
        super(id, title, description, type, dateTime);
    }

    public DailyTask() {
    }

    @Override
    public LocalDate getClosestDate(LocalDate currentDate) {
        LocalDate taskDate = this.getDateTime().toLocalDate();

        while (taskDate.isBefore(currentDate)){
            taskDate = taskDate.plusDays(1);
        }
        return taskDate;
    }
    //Однократная
    //Ежедневная
    //Еженедельная
    //Ежемесячная
    // Ежегодная
}

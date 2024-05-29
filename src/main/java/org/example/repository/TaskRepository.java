package org.example.repository;

import org.example.model.entity.AnnualTask;
import org.example.model.entity.MonthlyTask;
import org.example.model.entity.OneTimeTask;
import org.example.model.utils.Task;
import org.example.model.utils.TaskType;

import java.time.LocalDateTime;
import java.util.List;
// Класс, работающий с БД
public class TaskRepository {

    public List<Task> getAllTasks() {
        return List.of(
                new OneTimeTask(1, "Однократная задача", "Описание однократной задачи", TaskType.WORKING, LocalDateTime.now()),
                new MonthlyTask(2, "Ежемесячная задача", "Описание ежемесячной задачи", TaskType.WORKING, LocalDateTime.of(2024, 4, 24, 0, 0)),
                new AnnualTask(3, "Ежегодная задача", "Описание ежегодной задачи", TaskType.PERSONAL, LocalDateTime.of(2023, 5, 25, 0, 0))
        );
    }
}

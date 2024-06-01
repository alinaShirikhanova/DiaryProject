package org.example;

import org.example.model.utils.FrequencyType;
import org.example.model.utils.Task;
import org.example.model.utils.TaskType;
import org.example.repository.TaskRepository;
import org.example.service.TaskManager;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

////        TaskType type = TaskType.PERSONAL;
//        TaskType type = TaskType.valueOf("PERSONAL");
//        System.out.println(type);
//
//        TaskManager taskManager = new TaskManager();
//        System.out.println(LocalDateTime.now());
//
//        System.out.println(taskManager.getCurrentTasks(LocalDate.now()));
//        System.out.println(TaskRepository.getAllTasks());


        TaskManager taskManager = new TaskManager();
        System.out.println(taskManager.getTaskById(3));
        taskManager.deleteById(3);
//        taskManager.createTask("Сходить в зал", "Позаниматься", TaskType.PERSONAL, FrequencyType.WEEKLY, LocalDateTime.now());
//        TaskRepository taskRepository = new TaskRepository();
//        taskRepository.insertTask("Сходить в зал", "Позаниматься", "PERSONAL", Timestamp.valueOf(LocalDateTime.now()), "oneTime");


//        LocalDateTime localDateTime = Timestamp.valueOf("2024-05-29 00:00:00").toLocalDateTime();
//        System.out.println(localDateTime);
//        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("uuuu-MM-dd hh:mm:ss");
//        System.out.println(LocalDateTime.parse("2024-05-29 00:00:00", pattern));
    }

    // Реализовать возможность редактирования задачи
    // Реализовать получение всех задач по заданному типу (PERSONAL/WORKING)
    // Реализовать получение всех задач по заданному типу повторяемости (ONETIME/DAILY...)
    // Реализовать получение задач по заданному типу повторяемости (PERSONAL/WORKING) на сегодня
    // Реализовать получение всех задач по заданному типу повторяемости (ONETIME/DAILY...) на сегодня
}
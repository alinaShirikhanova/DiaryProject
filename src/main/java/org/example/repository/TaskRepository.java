package org.example.repository;

import org.example.model.entity.*;
import org.example.model.utils.Task;
import org.example.model.utils.TaskType;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

// Класс, работающий с БД
public class TaskRepository {

    private static String url = "jdbc:postgresql://localhost:5432/postgres";
    private static String login = "postgres";
    private static String password = "123";

    public  ArrayList<Task> getAllTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(url, login, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM diary.task");
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                String dateTime = resultSet.getString("date_time");
                String taskType = resultSet.getString("type");
                String frequencyType = resultSet.getString("frequency_type");
                tasks.add(getTaskByType(id, title, description, taskType, dateTime, frequencyType));
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return tasks;
    }

    public  void insertTask(String title, String description, String taskType, Timestamp dateTime, String frequencyType) {
        try {
            Connection connection = DriverManager.getConnection(url, login, password);
            PreparedStatement statement = connection.prepareStatement("INSERT INTO diary.task (title,  description, type, date_time, frequency_type) VALUES(?, ?, ?, ?, ?)");

            statement.setString(1, title);
            statement.setString(2, description);
            statement.setString(3, taskType);
            statement.setTimestamp(4, dateTime);
            statement.setString(5, frequencyType);
            statement.execute();

            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static Task getTaskByType(long id, String title, String description, String taskType, String dateTime, String frequencyType) {
        Task task = null;
        switch (frequencyType) {
            case "oneTime" -> task = new OneTimeTask();
            case "daily" -> task = new DailyTask();
            case "weekly" -> task = new WeeklyTask();
            case "monthly" -> task = new MonthlyTask();
            case "annual" -> task = new AnnualTask();
        }
        return createTask(task, id, title, description, taskType, dateTime);
    }


    private static Task createTask(Task task, long id, String title, String description, String taskType, String dateTime) {
        task.setId(id);
        task.setTitle(title);
        task.setDescription(description);
        task.setType(TaskType.valueOf(taskType));
        task.setDateTime(Timestamp.valueOf(dateTime).toLocalDateTime());
        return task;
    }

//    public List<Task> getAllTasks() {
////        return List.of(
////                new OneTimeTask(1, "Однократная задача", "Описание однократной задачи", TaskType.WORKING, LocalDateTime.now()),
////                new MonthlyTask(2, "Ежемесячная задача", "Описание ежемесячной задачи", TaskType.WORKING, LocalDateTime.of(2024, 4, 24, 0, 0)),
////                new AnnualTask(3, "Ежегодная задача", "Описание ежегодной задачи", TaskType.PERSONAL, LocalDateTime.of(2023, 5, 25, 0, 0))
////        );
//    }
}

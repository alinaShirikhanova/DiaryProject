package org.example.repository;

import org.example.model.entity.*;
import org.example.model.utils.FrequencyType;
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

    private  Connection connection;

    public TaskRepository() {
        try {
            this.connection = DriverManager.getConnection(url, login, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public ArrayList<Task> getAllTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
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


    public List<Task> getTasksByType(String type){
       List<Task> tasks = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM diary.task WHERE type = ?");
            statement.setString(1, type);
            ResultSet resultSet = statement.executeQuery();
            tasks = createListOfTasks(resultSet);

            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return tasks;
    }

    public void createTask(String title, String description, String type, String frequencyType, Timestamp dateTime) {
        try {

            PreparedStatement statement = connection.prepareStatement("INSERT INTO diary.task(title, description, type, frequency_type, date_time) VALUES(?, ?, ?, ?, ?)");
            statement.setString(1, title);
            statement.setString(2, description);
            statement.setString(3, type);
            statement.setString(4, frequencyType);
            statement.setTimestamp(5, dateTime);
            statement.execute();

            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Task getTaskById(long id) {
        Task task = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM diary.task WHERE id = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            String title = resultSet.getString("title");
            String description = resultSet.getString("description");
            String dateTime = resultSet.getString("date_time");
            String taskType = resultSet.getString("type");
            String frequencyType = resultSet.getString("frequency_type");
            task = getTaskByType(id, title, description, taskType, dateTime, frequencyType);
            connection.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return task;
    }

    public void deleteById(long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM diary.task WHERE id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
//    public  void insertTask(String title, String description, String taskType, Timestamp dateTime, String frequencyType) {
//        try {
//            Connection connection = DriverManager.getConnection(url, login, password);
//            PreparedStatement statement = connection.prepareStatement("INSERT INTO diary.task (title,  description, type, date_time, frequency_type) VALUES(?, ?, ?, ?, ?)");
//
//            statement.setString(1, title);
//            statement.setString(2, description);
//            statement.setString(3, taskType);
//            statement.setTimestamp(4, dateTime);
//            statement.setString(5, frequencyType);
//            statement.execute();
//
//            connection.close();
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//    }

    private static Task getTaskByType(long id, String title, String description, String taskType, String dateTime, String frequencyType) {
        Task task = null;
        switch (frequencyType) {
            case "ONETIME" -> task = new OneTimeTask();
            case "DAILY" -> task = new DailyTask();
            case "WEEKLY" -> task = new WeeklyTask();
            case "MONTHLY" -> task = new MonthlyTask();
            case "ANNUAL" -> task = new AnnualTask();
        }
        return createTask(task, id, title, description, taskType, frequencyType, dateTime);
    }


    public List<Task> createListOfTasks(ResultSet resultSet) throws SQLException {
        List<Task> tasks = new ArrayList<>();
        while (resultSet.next()) {
            long id = resultSet.getLong("id");
            String title = resultSet.getString("title");
            String description = resultSet.getString("description");
            String dateTime = resultSet.getString("date_time");
            String taskType = resultSet.getString("type");
            String frequencyType = resultSet.getString("frequency_type");
            tasks.add(getTaskByType(id, title, description, taskType, dateTime, frequencyType));
        }
        return tasks;
    }



    private static Task createTask(Task task, long id, String title, String description, String taskType, String frequencyType,  String dateTime) {
        task.setId(id);
        task.setTitle(title);
        task.setDescription(description);
        task.setType(TaskType.valueOf(taskType));
        task.setFrequencyType(FrequencyType.valueOf(frequencyType));
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

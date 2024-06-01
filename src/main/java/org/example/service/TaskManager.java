package org.example.service;

import org.example.model.utils.Task;
import org.example.model.utils.TaskType;
import org.example.repository.TaskRepository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private TaskRepository repository = new TaskRepository();


    public List<Task> getAllTasks(LocalDate date){
        return repository.getAllTasks();
    }
    public List<Task> getCurrentTasks(LocalDate date){
        List<Task> allTasks = repository.getAllTasks();
        List<Task> currentTasks = new ArrayList<>();

        for (Task task : allTasks){
            LocalDate closestDate = task.getClosestDate(date);
            if (date.isEqual(closestDate)){
                currentTasks.add(task);
            }
        }
        return currentTasks;
    }


    public void createTask(String title, String description, TaskType type, String frequencyType, Timestamp dateTime){

    }

}

package org.example.service;

import org.example.model.utils.Task;
import org.example.repository.TaskRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private TaskRepository repository = new TaskRepository();


    public List<Task> getAllTasks(LocalDate date){

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
}

package org.example;

import org.example.model.utils.Task;
import org.example.service.TaskManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        TaskManager taskManager = new TaskManager();
        System.out.println(taskManager.getCurrentTasks(LocalDate.now()));
    }
}
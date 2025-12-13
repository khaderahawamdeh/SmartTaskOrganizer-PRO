package service;

import model.Task;
import model.Priority;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {

    private List<Task> tasks;
    private int nextId = 1;

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    public Task addTask(String title, String description,
                        LocalDate deadline, Priority priority) {

        Task task = new Task(
                nextId,
                title,
                description,
                deadline,
                priority
        );

        tasks.add(task);
        nextId++;
        return task;
    }

    public List<Task> getAllTasks() {
        return tasks;
    }
}

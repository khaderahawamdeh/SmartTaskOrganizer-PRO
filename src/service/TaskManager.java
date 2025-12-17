package service;

import model.Task;
import model.TaskStatus;
import model.Priority;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;




public class TaskManager {

    private List<Task> tasks;
    private int nextId = 1;

    public TaskManager() {
    tasks = new ArrayList<>();
    loadTasksFromFile();
}



    public Task addTask(String title, String description,
            LocalDate deadline, Priority priority) {

        Task task = new Task(
                nextId,
                title,
                description,
                deadline,
                priority);

        tasks.add(task);
        nextId++;
        saveTasksToFile();
        return task;
    }

    // FR4 - View All Tasks
    public void printAllTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }

        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public void saveTasksToFile() {
    try {
        File dir = new File("data");
        if (!dir.exists()) {
            dir.mkdir();
        }

        FileWriter writer = new FileWriter("data/tasks.txt");

        for (Task task : tasks) {
            writer.write(
                task.getId() + "|" +
                task.getTitle() + "|" +
                task.getDescription() + "|" +
                task.getDeadline() + "|" +
                task.getPriority() + "|" +
                task.getStatus() + "\n"
            );
        }

        writer.close();
    } catch (IOException e) {
        System.out.println("Error saving tasks to file.");
    }
}


private void loadTasksFromFile() {
    File file = new File("data/tasks.txt");
    if (!file.exists()) {
        return;
    }

    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
        String line;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("\\|");

            int id = Integer.parseInt(parts[0]);
            String title = parts[1];
            String description = parts[2];
            LocalDate deadline = LocalDate.parse(parts[3]);
            Priority priority = Priority.valueOf(parts[4]);
            TaskStatus status = TaskStatus.valueOf(parts[5]);

            Task task = new Task(id, title, description, deadline, priority);
            task.setStatus(status);

            tasks.add(task);

            if (id >= nextId) {
                nextId = id + 1;
            }
        }
    } catch (Exception e) {
        System.out.println("Error loading tasks from file.");
    }
}



   public boolean editTaskPartial(int id,
        String newTitle,
        String newDescription,
        LocalDate newDeadline,
        Priority newPriority) {

    for (Task task : tasks) {
        if (task.getId() == id) {

            if (newTitle != null)
                task.setTitle(newTitle);

            if (newDescription != null)
                task.setDescription(newDescription);

            if (newDeadline != null)
                task.setDeadline(newDeadline);

            if (newPriority != null)
                task.setPriority(newPriority);

            saveTasksToFile();
            return true;
        }
    }
    return false;
}


}

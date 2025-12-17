package service;

import model.Task;
import model.Priority;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


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

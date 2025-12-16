import model.Priority;
import service.TaskManager;

import java.time.LocalDate;
import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        TaskManager manager = new TaskManager();

        while (true) {
            System.out.println("\n=== Smart Task Organizer ===");
            System.out.println("1. Create Task");
            System.out.println("2. Edit Task");
            System.out.println("3. View All Tasks");
            System.out.println("4. Exit");
            System.out.print("Choose option: ");

            int choice = Integer.parseInt(input.nextLine());

            if (choice == 1) {
                System.out.print("Title: ");
                String title = input.nextLine();

                System.out.print("Description: ");
                String description = input.nextLine();

                System.out.print("Deadline (YYYY-MM-DD): ");
                LocalDate deadline = LocalDate.parse(input.nextLine());

                System.out.print("Priority (HIGH, MEDIUM, LOW): ");
                Priority priority =
                        Priority.valueOf(input.nextLine().toUpperCase());

                manager.addTask(title, description, deadline, priority);
                System.out.println("Task added successfully!");

            } else if (choice == 2) {
                System.out.print("Enter task ID to edit: ");
                int id = Integer.parseInt(input.nextLine());

                System.out.print("New title: ");
                String newTitle = input.nextLine();

                System.out.print("New description: ");
                String newDescription = input.nextLine();

                System.out.print("New deadline (YYYY-MM-DD): ");
                LocalDate newDeadline = LocalDate.parse(input.nextLine());

                System.out.print("New priority (HIGH, MEDIUM, LOW): ");
                Priority newPriority =
                        Priority.valueOf(input.nextLine().toUpperCase());

                boolean updated = manager.editTask(
                        id, newTitle, newDescription, newDeadline, newPriority
                );

                if (updated) {
                    System.out.println("Task updated!");
                } else {
                    System.out.println("Task not found!");
                }

            } else if (choice == 3) {
                manager.printAllTasks();

            } else if (choice == 4) {
                System.out.println("Goodbye!");
                break;
            } else {
                System.out.println("Invalid option!");
            }
        }
    }
}
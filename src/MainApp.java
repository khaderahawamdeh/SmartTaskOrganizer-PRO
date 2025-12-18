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
            System.out.println("3. Delete Task");
            System.out.println("4. View All Tasks");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");

            int choice = Integer.parseInt(input.nextLine());

            // ================= CREATE =================
            if (choice == 1) {
                System.out.print("Title: ");
                String title = input.nextLine();

                System.out.print("Description: ");
                String description = input.nextLine();

                System.out.print("Deadline (YYYY-MM-DD): ");
                LocalDate deadline = LocalDate.parse(input.nextLine());

                System.out.print("Priority (HIGH, MEDIUM, LOW): ");
                Priority priority = Priority.valueOf(input.nextLine().toUpperCase());

                manager.addTask(title, description, deadline, priority);
                System.out.println("Task added successfully!");

            }

            // ================= EDIT =================
            else if (choice == 2) {

                if (manager.getAllTasks().isEmpty()) {
                    System.out.println("No tasks available.");
                    continue;
                }

                manager.printAllTasks();

                System.out.print("Enter task ID to edit: ");
                int id = Integer.parseInt(input.nextLine());

                System.out.println("What do you want to edit?");
                System.out.println("1. Title");
                System.out.println("2. Description");
                System.out.println("3. Deadline");
                System.out.println("4. Priority");
                System.out.print("Choose option: ");

                int editChoice = Integer.parseInt(input.nextLine());

                String newTitle = null;
                String newDescription = null;
                LocalDate newDeadline = null;
                Priority newPriority = null;

                switch (editChoice) {
                    case 1:
                        System.out.print("New title: ");
                        newTitle = input.nextLine();
                        break;

                    case 2:
                        System.out.print("New description: ");
                        newDescription = input.nextLine();
                        break;

                    case 3:
                        System.out.print("New deadline (YYYY-MM-DD): ");
                        newDeadline = LocalDate.parse(input.nextLine());
                        break;

                    case 4:
                        System.out.print("New priority (HIGH, MEDIUM, LOW): ");
                        newPriority = Priority.valueOf(input.nextLine().toUpperCase());
                        break;

                    default:
                        System.out.println("Invalid choice.");
                        continue;
                }

                boolean updated = manager.editTaskPartial(
                        id, newTitle, newDescription, newDeadline, newPriority);

                if (updated) {
                    System.out.println("Task updated successfully!");
                } else {
                    System.out.println("Task not found!");
                }
            }

            // ================= DELETE =================
            else if (choice == 3) {

                if (manager.getAllTasks().isEmpty()) {
                    System.out.println("No tasks available.");
                    continue;
                }

                manager.printAllTasks();

                System.out.print("Enter task ID to delete: ");
                int id = Integer.parseInt(input.nextLine());

                boolean deleted = manager.deleteTask(id);

                if (deleted) {
                    System.out.println("Task deleted successfully!");
                } else {
                    System.out.println("Task not found!");
                }
            }

            // ================= VIEW =================
            else if (choice == 4) {
                manager.printAllTasks();
            }

            // ================= EXIT =================
            else if (choice == 5) {
                System.out.println("Goodbye!");
                break;
            }

            else {
                System.out.println("Invalid option!");
            }
        }
    }
}

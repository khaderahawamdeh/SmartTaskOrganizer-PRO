import model.Priority;
import model.Task;
import service.TaskManager;

import java.time.LocalDate;
import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        TaskManager manager = new TaskManager();

        System.out.println("=== Smart Task Organizer ===");

        System.out.print("Enter title: ");
        String title = input.nextLine();

        System.out.print("Enter description: ");
        String description = input.nextLine();

        System.out.print("Enter deadline (YYYY-MM-DD): ");
        LocalDate deadline = LocalDate.parse(input.nextLine());

        System.out.print("Enter priority (HIGH, MEDIUM, LOW): ");
        Priority priority =
                Priority.valueOf(input.nextLine().toUpperCase());

        Task task = manager.addTask(
                title,
                description,
                deadline,
                priority
        );

        System.out.println("\nTask created successfully!");
        System.out.println(task);
    }
}



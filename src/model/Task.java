package model;

import java.time.LocalDate;

public class Task {

    private int id;
    private String title;
    private String description;
    private LocalDate deadline;
    private Priority priority;
    private TaskStatus status;

    public Task(int id, String title, String description,
                LocalDate deadline, Priority priority) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.priority = priority;
        this.status = TaskStatus.TODO; // default
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public Priority getPriority() {
        return priority;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task {" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", deadline=" + deadline +
                ", priority=" + priority +
                ", status=" + status +
                '}';
    }
}

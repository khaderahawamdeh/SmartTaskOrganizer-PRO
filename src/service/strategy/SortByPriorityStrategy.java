package service.strategy;

import model.Task;
import java.util.List;

public class SortByPriorityStrategy implements SortStrategy {

    @Override
    public void sort(List<Task> tasks) {
        tasks.sort((t1, t2) -> t1.getPriority().compareTo(t2.getPriority()));
    }
}


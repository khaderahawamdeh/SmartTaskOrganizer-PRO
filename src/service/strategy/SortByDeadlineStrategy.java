package service.strategy;

import model.Task;
import java.util.List;

public class SortByDeadlineStrategy implements SortStrategy {

    @Override
    public void sort(List<Task> tasks) {
        tasks.sort((t1, t2) -> t1.getDeadline().compareTo(t2.getDeadline()));
    }
}


package seedu.address.model.task;

import java.util.Comparator;

import seedu.address.model.date.Date;

/**
 * Compares two tasks by date.
 */
public class TaskSortByDateComparator implements Comparator<Task> {
    
    /**
     * Compares {@code taskA} and {@code taskB} by date.
     */
    public int compare(Task taskA, Task taskB) {
        
        Date firstDate = taskA.getDate();
        Date secondDate = taskB.getDate();

        return firstDate.compareTo(secondDate);
    }
}
package seedu.address.model.task;

import java.util.Comparator;

import seedu.address.model.date.Date;

/**
 * Compares two tasks by date.
 */
public class TaskSortByDateAndCompletionComparator implements Comparator<Task> {
    
    /**
     * Compares {@code taskA} and {@code taskB} by date.
     */
    public int compare(Task taskA, Task taskB) {
        
        Date firstDate = taskA.getDate();
        Date secondDate = taskB.getDate();

        switch(compareCompletion(taskA, taskB)) {
            case 1:
                return 1;
            case -1:
                return -1;
            case 0:
                return firstDate.compareTo(secondDate);
        }

        return firstDate.compareTo(secondDate);
    }

    public int compareCompletion(Task taskA, Task taskB) {

        boolean firstTaskCompleted = taskA.isCompleted();
        boolean secondTaskCompleted = taskB.isCompleted();

        if (firstTaskCompleted && !secondTaskCompleted) {
            return 1;
        } else if (!firstTaskCompleted && secondTaskCompleted) {
            return -1;
        } else {
            return 0;
        }
    }
}
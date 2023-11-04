package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.task.TaskBuilder;

public class TaskSortByDateAndCompletionComparatorTest {

    @Test
    public void firstTaskCompletedButEarlierThanSecondTask_returnsTrue() {
        Task taskA = new TaskBuilder().withDate("2023-10-23").withIsCompleted(true).build();
        Task taskB = new TaskBuilder().withDate("2023-10-24").withIsCompleted(false).build();
        TaskSortByDateAndCompletionComparator comp = new TaskSortByDateAndCompletionComparator();
        assertTrue(comp.compare(taskA, taskB) > 0);
    }

    @Test
    public void firstTaskNotCompletedAndLaterThanSecondTask_returnsTrue() {
        Task taskA = new TaskBuilder().withDate("2023-10-23").withIsCompleted(false).build();
        Task taskB = new TaskBuilder().withDate("2023-10-24").withIsCompleted(false).build();
        TaskSortByDateAndCompletionComparator comp = new TaskSortByDateAndCompletionComparator();
        assertTrue(comp.compare(taskA, taskB) < 0);
    }

    @Test
    public void firstTaskCompletedAndSecondTaskNotCompleted_returnsTrue() {
        Task taskA = new TaskBuilder().withDate("2023-10-23").withIsCompleted(true).build();
        Task taskB = new TaskBuilder().withDate("2023-10-24").withIsCompleted(false).build();
        TaskSortByDateAndCompletionComparator comp = new TaskSortByDateAndCompletionComparator();
        assertEquals(comp.compareCompletion(taskA, taskB), 1);
    }

    @Test
    public void firstTaskNotCompletedAndSecondTaskCompleted_returnsTrue() {
        Task taskA = new TaskBuilder().withDate("2023-10-23").withIsCompleted(false).build();
        Task taskB = new TaskBuilder().withDate("2023-10-24").withIsCompleted(true).build();
        TaskSortByDateAndCompletionComparator comp = new TaskSortByDateAndCompletionComparator();
        assertEquals(comp.compareCompletion(taskA, taskB), -1);
    }

    @Test
    public void allTasksCompleted_returnsTrue() {
        Task taskA = new TaskBuilder().withDate("2023-10-23").withIsCompleted(true).build();
        Task taskB = new TaskBuilder().withDate("2023-10-24").withIsCompleted(true).build();
        TaskSortByDateAndCompletionComparator comp = new TaskSortByDateAndCompletionComparator();
        assertTrue(comp.compare(taskA, taskB) < 0);
        assertEquals(comp.compareCompletion(taskA, taskB), 0);
    }
}

package seedu.address.model.task;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.task.TaskBuilder;

import static org.junit.jupiter.api.Assertions.*;

public class TaskInReminderPredicateTest {

    @Test
    public void equals() {
        TaskInReminderPredicate pred1 = new TaskInReminderPredicate();
        TaskInReminderPredicate pred2 = new TaskInReminderPredicate();
        assertEquals(pred1, pred2);
    }

    @Test
    public void taskOverdueIsInReminder_returnsTrue() {
        TaskInReminderPredicate predicate = new TaskInReminderPredicate();
        Task taskA = new TaskBuilder().withDate("2023-10-23").withIsCompleted(false).build();
        assertTrue(predicate.test(taskA));
    }

    @Test
    public void taskDeadlineWithinThreeDays_returnsTrue() {
        TaskInReminderPredicate predicate = new TaskInReminderPredicate();
        Task taskA = new TaskBuilder().withDate("2023-11-06").withIsCompleted(false).build();
        assertTrue(predicate.test(taskA));
    }

    @Test
    public void taskDeadlineNotWithinThreeDays_returnsFalse() {
        TaskInReminderPredicate predicate = new TaskInReminderPredicate();
        Task taskA = new TaskBuilder().withDate("2099-10-12").withIsCompleted(false).build();
        assertFalse(predicate.test(taskA));
    }
}

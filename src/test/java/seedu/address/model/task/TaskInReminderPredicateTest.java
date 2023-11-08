package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.task.TaskBuilder;

public class TaskInReminderPredicateTest {

    @Test
    public void equals() {

        // same object -> returns true
        TaskInReminderPredicate pred1 = new TaskInReminderPredicate();
        assertTrue(pred1.equals(pred1));

        // null -> returns false
        assertFalse(pred1.equals(null));

        // different objects -> returns true
        TaskInReminderPredicate pred2 = new TaskInReminderPredicate();
        assertTrue(pred1.equals(pred2));
    }

    @Test
    public void taskOverdueIsInReminder_returnsTrue() {
        TaskInReminderPredicate predicate = new TaskInReminderPredicate();
        Task taskA = new TaskBuilder().withDate("2023-10-23").withIsCompleted(false).build();
        assertTrue(predicate.test(taskA));
    }

    @Test
    public void taskDeadlineNotWithinThreeDays_returnsFalse() {
        TaskInReminderPredicate predicate = new TaskInReminderPredicate();
        Task taskA = new TaskBuilder().withDate("2099-10-12").withIsCompleted(false).build();
        assertFalse(predicate.test(taskA));
    }
}
